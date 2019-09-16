/*
 * Copyright (c)  2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package io.siddhi.core.stream.input.source;

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.annotation.Parameter;
import io.siddhi.annotation.ParameterOverload;
import io.siddhi.annotation.util.DataType;
import io.siddhi.core.config.SiddhiAppContext;
import io.siddhi.core.exception.ConnectionUnavailableException;
import io.siddhi.core.stream.ServiceDeploymentInfo;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.core.util.transport.InMemoryBroker;
import io.siddhi.core.util.transport.OptionHolder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link Source} to receive events through in-memory transport.
 */
@Extension(
        name = "inMemory",
        namespace = "source",
        description = "In-memory source subscribes to a topic to consume events which are published on the " +
                "same topic by In-memory sinks. " +
                "This provides a way to connect multiple Siddhi Apps deployed under the same Siddhi Manager (JVM). " +
                "Here both the publisher and subscriber should have the same event schema (stream definition) " +
                "for successful data transfer.",
        parameters = @Parameter(name = "topic.list", type = DataType.STRING,
                description = "Subscribes to the events sent on the given topics. This list " +
                        "can be provided as a set of comma-separated values. " +
                        "e.g., `topic_one,topic_two`"),
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"topic.list"})
        },
        examples = @Example(
                syntax = "@source(type='inMemory', topics='Stocks', @map(type='passThrough'))\n" +
                        "define stream StocksStream (symbol string, price float, volume long);",
                description = "Here the `StocksStream` uses inMemory source to consume events published " +
                        "on the topic `Stocks` by the inMemory sinks deployed in the same JVM."
        )
)

public class InMemorySource extends Source {
    private static final Logger LOG = Logger.getLogger(InMemorySource.class);
    private static final String TOPIC_KEY = "topic.list";
    public static final String HEADER_SEPARATOR = ",";
    private String topics[];
    private SourceEventListener sourceEventListener;
    private List<InMemoryBroker.Subscriber> subscribers = new ArrayList<>();

    @Override
    protected ServiceDeploymentInfo exposeServiceDeploymentInfo() {
        return null;
    }

    @Override
    public StateFactory<State> init(SourceEventListener sourceEventListener, OptionHolder optionHolder,
                                    String[] requestedTransportPropertyNames, ConfigReader configReader,
                                    SiddhiAppContext siddhiAppContext) {
        this.sourceEventListener = sourceEventListener;
        String topicList = optionHolder.validateAndGetStaticValue(TOPIC_KEY, "input inMemory source");
        topics = topicList.split(HEADER_SEPARATOR);

        for (String topic : topics) {
            this.subscribers.add(new InMemoryBroker.Subscriber() {
                @Override
                public void onMessage(Object event) {
                    sourceEventListener.onEvent(event, null);
                }

                @Override
                public String getTopic() {
                    return topic;
                }
            });
        }
        return null;
    }

    @Override
    public Class[] getOutputEventClasses() {
        return new Class[]{};
    }

    @Override
    public void connect(ConnectionCallback connectionCallback, State state) throws ConnectionUnavailableException {
        for (InMemoryBroker.Subscriber subscriber : subscribers) {
            InMemoryBroker.subscribe(subscriber);
        }
    }

    @Override
    public void disconnect() {
        for (InMemoryBroker.Subscriber subscriber : subscribers) {
            InMemoryBroker.unsubscribe(subscriber);
        }
    }

    @Override
    public void destroy() {
        // do nothing
    }

    @Override
    public void pause() {
        for (InMemoryBroker.Subscriber subscriber : subscribers) {
            InMemoryBroker.unsubscribe(subscriber);
        }
    }

    @Override
    public void resume() {
        for (InMemoryBroker.Subscriber subscriber : subscribers) {
            InMemoryBroker.subscribe(subscriber);
        }
    }

}
