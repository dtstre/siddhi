/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.siddhi.core.query.output.ratelimit.snapshot;


import io.siddhi.core.config.SiddhiQueryContext;
import io.siddhi.core.event.ComplexEvent;
import io.siddhi.core.event.ComplexEventChunk;
import io.siddhi.core.event.stream.StreamEventPool;
import io.siddhi.core.util.Scheduler;
import io.siddhi.core.util.parser.SchedulerParser;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parent implementation for per event periodic snapshot rate limiting. Multiple implementations of this will be
 * there to represent different queries. Snapshot rate limiting will only emit current events representing the
 * snapshot period.
 */
public class PerSnapshotOutputRateLimiter
        extends SnapshotOutputRateLimiter<PerSnapshotOutputRateLimiter.RateLimiterState> {
    private final Long value;
    private ComplexEvent lastEvent;
    private Scheduler scheduler;
    private long scheduledTime;

    public PerSnapshotOutputRateLimiter(Long value,
                                        WrappedSnapshotOutputRateLimiter wrappedSnapshotOutputRateLimiter,
                                        boolean groupBy, SiddhiQueryContext siddhiQueryContext) {
        super(wrappedSnapshotOutputRateLimiter, siddhiQueryContext, groupBy);
        this.value = value;
    }

    @Override
    protected StateFactory<RateLimiterState> init() {
        return () -> new RateLimiterState();
    }

    @Override
    public void process(ComplexEventChunk complexEventChunk) {
        List<ComplexEventChunk<ComplexEvent>> outputEventChunks = new ArrayList<ComplexEventChunk<ComplexEvent>>();
        complexEventChunk.reset();
        RateLimiterState state = stateHolder.getState();
        try {
            synchronized (state) {
                while (complexEventChunk.hasNext()) {
                    ComplexEvent event = complexEventChunk.next();
                    if (event.getType() == ComplexEvent.Type.TIMER) {
                        tryFlushEvents(outputEventChunks, event);
                    } else if (event.getType() == ComplexEvent.Type.CURRENT) {
                        complexEventChunk.remove();
                        tryFlushEvents(outputEventChunks, event);
                        lastEvent = event;
                    } else {
                        tryFlushEvents(outputEventChunks, event);
                    }
                }
            }
        } finally {
            stateHolder.returnState(state);
        }
        for (ComplexEventChunk eventChunk : outputEventChunks) {
            sendToCallBacks(eventChunk);
        }
    }

    private void tryFlushEvents(List<ComplexEventChunk<ComplexEvent>> outputEventChunks, ComplexEvent event) {
        if (event.getTimestamp() >= scheduledTime) {
            ComplexEventChunk<ComplexEvent> outputEventChunk = new ComplexEventChunk<ComplexEvent>(false);
            if (lastEvent != null) {
                outputEventChunk.add(cloneComplexEvent(lastEvent));
            }
            outputEventChunks.add(outputEventChunk);
            scheduledTime += value;
            scheduler.notifyAt(scheduledTime);
        }
    }

    @Override
    public void start() {
        scheduler = SchedulerParser.parse(this, siddhiQueryContext);
        scheduler.setStreamEventPool(new StreamEventPool(0, 0, 0, 5));
        scheduler.init(lockWrapper, siddhiQueryContext.getName());
        long currentTime = System.currentTimeMillis();
        scheduledTime = currentTime + value;
        scheduler.notifyAt(scheduledTime);
    }

    @Override
    public void stop() {
        //Nothing to stop
    }

    class RateLimiterState extends State {

        private ComplexEventChunk<ComplexEvent> eventChunk = new ComplexEventChunk<ComplexEvent>(false);

        @Override
        public boolean canDestroy() {
            return eventChunk.getFirst() == null;
        }

        @Override
        public Map<String, Object> snapshot() {
            Map<String, Object> state = new HashMap<>();
            state.put("EventChunk", eventChunk.getFirst());
            return state;
        }

        @Override
        public void restore(Map<String, Object> state) {
            eventChunk.clear();
            eventChunk.add((ComplexEvent) state.get("EventList"));
        }
    }

}
