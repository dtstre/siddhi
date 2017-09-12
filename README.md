WSO2 Siddhi 
===========

Siddhi is a java library that listens to events from data streams, detect complex conditions described via a **Streaming
 SQL language**, and trigger actions. It can be used to do both **_Stream Processing_** and 
 **_Complex Event Processing_**. Siddhi is free and open source, under **Apache Software License v2.0**. 

It can be used for; 

* Data preprocessing
* Generate alerts based on thresholds
* Calculate aggregations within a short window and over long time period
* Joining multiple data streams
* Data correlation while finding missing and erroneous events
* Interact streaming data with databases
* Detecting temporal event patterns
* Tracking (something over space or time)
* Trend analysis (rise, fall, turn, tipple bottom)
* Realtime predictions with existing and online machine learning models
* And more ... <a target="_blank" href="http://www.kdnuggets.com/2015/08/patterns-streaming-realtime-analytics.html">“Patterns of Streaming Realtime Analytics”</a>

Siddhi was initiated as a joint research project between WSO2 and and University of Moratuwa, Sri Lanka.

## Why use Siddhi ? 

* Fast, that <a target="_blank" href="http://wso2.com/library/conference/2017/2/wso2con-usa-2017-scalable-real-time-complex-event-processing-at-uber?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">UBER</a> 
use it to process 20 Billion events per day (300,000 events per second). 
* Lightweight (<2MB), embeddable in Android and RaspberryPi
* Solutions based on Siddhi has been a finalist at <a target="_blank" href="http://dl.acm.org/results.cfm?query=(%252Bgrand%20%252Bchallenge%20%252Bwso2)&within=owners.owner=HOSTED&filtered=&dte=&bfr=">ACM DEBS Grand Challenge Stream Processing competitions in 2014, 2015, 2016, 2017</a>.
* Been basis of many academic research projects and have <a target="_blank" href="https://scholar.google.com/scholar?cites=5113376427716987836&as_sdt=2005&sciodt=0,5&hl=en">60+ citations</a>. 

## Who use Siddhi ? 
* Siddhi is also heavily used by WSO2 in their products such as <a target="_blank" href="http://wso2.com/analytics?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">WSO2 Data Analytics Server</a> 
   and <a target="_blank" href="https://github.com/wso2/product-sp">WSO2 Stream Processor</a> to provide stream processing capabilities. 
   Siddhi is also the **edge analytics** library of [WSO2 IoT Server](http://wso2.com/iot?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17), core of <a target="_blank" href="http://wso2.com/api-management?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">WSO2 API Manager</a>'s throttling, and the core of 
   <a target="_blank" href="http://wso2.com/platform?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">WSO2 products'</a> analytics.
* <a target="_blank" href="http://wso2.com/library/conference/2017/2/wso2con-usa-2017-scalable-real-time-complex-event-processing-at-uber?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">UBER</a>
* <a target="_blank" href="http://eagle.apache.org/docs/index.html">Apache Eagle</a> 
* 60+ companies including many Fortune 500 companies use Siddhi in production 


If you are using Siddhi, we would love to hear more. 

## Try Siddhi with <a target="_blank" href="https://github.com/wso2/product-sp">WSO2 Stream Processor</a>

To get following capabilities:  
* **Siddhi Query Editor** 
* **Siddhi Debugger**
* **Event Simulator** 
* Run Siddhi as a Server with High Availability and Scalability
* Monitoring support for Siddhi
* Realtime dashboard 
* Business user friendly query generation UI and deployment

<a target="_blank" href="https://github.com/wso2/product-sp">WSO2 Stream Processor</a> is also open source released under
 **Apache Software License v2.0**, and the server version of Siddhi was a 
Strong Performer in <a target="_blank" href="https://go.forrester.com/blogs/16-04-16-15_true_streaming_analytics_platforms_for_real_time_everything/">The Forrester Wave™: Big Data Streaming Analytics, Q1 2016</a> (<a target="_blank" href="https://www.forrester.com/report/The+Forrester+Wave+Big+Data+Streaming+Analytics+Q1+2016/-/E-RES129023">Report</a>) 
and a <a target="_blank" href="https://www.gartner.com/doc/3314217/cool-vendors-internet-things-analytics">Cool Vendors in Internet of Things Analytics, 2016</a>. 

There are domain specific solutions built using Siddhi, including <a target="_blank" href="https://wso2.com/analytics/solutions?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">Fraud Detection, Stock Market Surveillance, Location analytics, Proximity Marketing, Contextual Recommendation, Ad Optimization, Operational Analytics, and Detecting Chart Patterns</a>. 

If you want more information please contact us via <a target="_blank" href="http://wso2.com/support?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">http://wso2.com/support/</a>.

## Useful links

* <a target="_blank" href="https://github.com/wso2/siddhi">Source code</a>
* <a target="_blank" href="https://github.com/wso2/siddhi/releases">Releases</a>
* <a target="_blank" href="https://github.com/wso2/siddhi/issues">Issue tracker</a>
* <a target="_blank" href="https://wso2.github.io/siddhi/extensions/">Siddhi Extensions</a>
* <a target="_blank" href="https://wso2.github.io/siddhi/#support">Support for Siddhi</a>

## Siddhi Versions

 <a target="_blank" href=""></a> 
 
* **Active development version of Siddhi** : **v4.0.0**  _built on Java 8._ 
 
    <a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/">Siddhi Query Guide</a> for Siddhi v4.x.x

* **Latest Stable Release of Siddhi** : **v3.0.5** _built on Java 7._

    <a target="_blank" href="https://docs.wso2.com/display/DAS310/Siddhi+Query+Language">Siddhi Query Guide</a> for Siddhi v3.x.x

## Latest API Docs 

Latest API Docs is <a target="_blank" href="https://wso2.github.io/siddhi/api/4.0.0-M69">4.0.0-M69</a>.

## Jenkins Build Status

|  Siddhi Branch | Jenkins Build Status |
| :---------------------------------------- |:---------------------------------------
| master         | [![Build Status](https://wso2.org/jenkins/view/wso2-dependencies/job/siddhi/job/siddhi/badge/icon)](https://wso2.org/jenkins/view/wso2-dependencies/job/siddhi/job/siddhi )|


## How to use 

**Using the Siddhi in <a target="_blank" href="https://github.com/wso2/product-sp">WSO2 Stream Processor</a>**

* You can use Siddhi in the latest <a target="_blank" href="https://github.com/wso2/product-sp/releases">WSO2 Stream Processor</a> that is a part of <a target="_blank" href="http://wso2.com/analytics?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">WSO2 Analytics</a> offering, with editor, debugger and simulation support. 

* All <a target="_blank" href="https://wso2.github.io/siddhi/extensions/">Siddhi extensions</a> are shipped by default with WSO2 Stream Processor.

**Using Siddhi as a <a target="_blank" href="https://wso2.github.io/siddhi/documentation/running-as-a-java-library">java library</a>**

* To embed Siddhi as a library in your project by adding the Siddhi libraries as dependencies to your project.

## System Requirements
1. Minimum memory - 500 MB (based on in-memory data stored for processing)
2. Processor      - Pentium 800MHz or equivalent at minimum
3. Java SE Development Kit 1.8 (1.7 for 3.x version)
4. To build Siddhi CEP from the Source distribution, it is necessary that you have
   JDK 1.8 version (1.7 for 3.x version) or later and Maven 3.0.4 or later

## How to Contribute
* Please report issues at <a target="_blank" href="https://github.com/wso2/siddhi/issues">GitHub Issue Tracker</a>.
* Send your contributions as pull requests to <a target="_blank" href="https://github.com/wso2/siddhi/tree/master">master branch</a>. 
 
## Contact us 
 * Post your questions with the <a target="_blank" href="http://stackoverflow.com/search?q=siddhi">"Siddhi"</a> tag in <a target="_blank" href="http://stackoverflow.com/search?q=siddhi">Stackoverflow</a>. 
 * For more details and support contact us via <a target="_blank" href="http://wso2.com/support?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">http://wso2.com/support/</a>
 
## Support 
* We are committed to ensuring support for Siddhi (with it's extensions) and <a target="_blank" href="https://github.com/wso2/product-sp">WSO2 Stream Processor</a> in production. 
* Our unique approach ensures that all support leverages our open development methodology and is provided by the very same engineers who build the technology. 
* For more details and to take advantage of this unique opportunity contact us via <a target="_blank" href="http://wso2.com/support?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">http://wso2.com/support/</a>. 
