This repo has the code for following challenges
- myRetail restful service
- comment moderation
- Generic notification service
- Real time data process

First three problem statement are addressed in microservice 
 - https://github.com/prasadinna/assessment/tree/develop/myRetail
 
Real time data process is addressed at microservice
 - https://github.com/prasadinna/assessment/tree/develop/ordergeneration
 
 
The microservice is built using spring boot application(In some of the cases my preference
was to use reactive Vertx as server but due to time contraint i could not do so)


myRetail restful service
-------------------------
Simple CRUD based microservice. For the code challenge in memory DB H2 is loaded. The 
schema and data load is achieved using data.sql and schema.sql. All the required code
is under -assessment/tree/develop/myRetail/src/main/java/com/myretail/app/products


Todo
 - use flyway as DB migration tool and better db schema
 - Use future or futurecompletion to merge result from different URL
 - Better error handling with good user experience

From terminal following rest end point can be triggered

-Product details
curl -v -X GET -H "Content-Type: application/json" \
 http://localhost:8085/api/product/v1/details/Prod3/
        
-Create Product
curl -v -X POST -H "Content-Type: application/json" \
        --data-binary '{"id":"Prod3","name":"Product3","price":{"amount":105,"currency":"USD"},"errorCode":0,"errorMessage":null}' \
        http://localhost:8085/api/product/v1/createdproduct/Prod3/ 
        
-Uppdate product    
curl -v -X PUT -H "Content-Type: application/json" \
        --data-binary '{"id":"Prod3","name":"Product3","price":{"amount":110,"currency":"USD"},"errorCode":0,"errorMessage":null}' \
        http://localhost:8085/api/product/v1/updateproduct/Prod3/


comment moderation
------------------
The core part of the system is built on lucene indexing process and using the custom 
tokenizer and filters(on top of standard filters such as stopfilter and porterfilters)
The custom filter gets the objection content from configuration and checks token stream
has the objection word. The main advantage using lucene it helps index and search for later
pont, we can pinpoint the location of the objection word

Todo
 - using DB to build the required datastore
 - handling localizations
 - workflow engine to manage the moderation cycle
 - UI dashboard for the moderator
 - queuing system for the group of moderator


All required code is under folder
https://github.com/prasadinna/assessment/tree/develop/myRetail/src/main/java/com/myretail/app/comment

testing objection words used
  		objectionKeys.add("infamous");
		objectionKeys.add("thief");
		objectionKeys.add("murder");
		objectionKeys.add("kill");
		objectionKeys.add("miser");
		objectionKeys.add("imprison");
		
post comment by guest or user
  sample 1 - no objection content
  
  curl -v -X POST -H "Content-Type: application/json" \
        --data-binary '{"userId":"user1","creationDate":"103","documentReference":"doc123","commentContent":"no malcontent found a good supplier","commentStatus":"SUBMIT", "parentId":null,"childIds":[]}' \
        http://localhost:8085/api/comment/v1/create 

  sample 2 - with objection content
   curl -v -X POST -H "Content-Type: application/json" \
        --data-binary '{"userId":"user1","creationDate":"103","documentReference":"doc123","commentContent":"thief suppplier killing him is an option","commentStatus":"SUBMIT", "parentId":null,"childIds":[]}' \
        http://localhost:8085/api/comment/v1/create 

 moderator can get the comment with objection content as - need to protect this api
  curl -v -X GET -H "Content-Type: application/json" http://localhost:8085/api/comment/v1/obbjectionslist  
  
Real time data process
----------------------
This system is built on the concept of reactive design. It has three part 
 - high volume order generator
 - search order based on any fields
 - real time order generation monitor
 
 The system uses the even driven mechanism to generate the order. The order generator system
 register the consumer for the order event. One current consumer is solr index client. Other
 client could e to pull the data over web socket to display on the UI
 
 
 To run the server, provide following configuration and the server starts on default port(8080)
 app.local.solr.home= /Users/i307242/assessment/assessment/ordergeneration/target/classes/solr-home
 
 The api /repo/startorder/{number} - takes the number of order to generate and return the time in
 millis taken by the server to generate so many order
 
 api -/repo/query/{field}/{value} is used for searching on any fields on the document generated
 
 technology and framework
  In memory Solr for searching on order 
  JPa and Solr spring integration for data index
  Custom sequence generator based on time stamp, nodeid and a counter - for generating the order id
 
 Todo
  Websocket based communication for the real time monitoring - wanted to use vertx server
  Real time data pull api - just need to add a listener and use websocket to display the result
  Distributed cache and db for fail-over
  
Generic notification service
----------------------------
The system is developed based on the producer consumer pattern and event driven patter. Main component of the systems are

 MessageModel - has Payload(string content or document),channel(email, slack), topic(such as order workflow, optional) and partition(optional)  
 Message center - central hub for receiving the message. It has an api to register the channel processor. The code has dummy channel processor for email and slack
 MessageChannelProcessor - specialised handler for sending message over the channel such as email and slack. This also maintain the topic and a Partition. If there is no topic in message, default topic is used for general purpose
 PartitionProcessor - each topic will have a partition(default partition is used in case there is no partition mentioned)
 
 Once message is ready to send from PartitionProcessor, document from the message model is used to get the client list and formatting the message
 
 I have not written end point to test the system, instead used MessageCenterTest to test the message ordering
 
 TODO
 - automated test, code coverage
 - take the system into microservice world and horizontal auto scaling - using service discovery and vaults 
 - fault tolerence - error recovery, prevention from attack, worker thread performance
 - develop a monitoring dashboard and run time configuration change and alerts, splunk for log analysys
 - perf improvement if required
 - dynamic channel registration
 - enhance channel processor  to dynamically pull data from source system(ex:email ids, email preferefeneces)
 - using kafka and quartz to manage the system
 - distributed cache and db to manage the message persistence
 - log analysis and machine learning to identify the usage pattern
 
 Team composition
 - devops - deploy on public cloud and develop monitoring tool and help with kafka redis deployment
 - SDET - functional and integration testing, verify code coverage and fortify analysis. Also jmeter perf test
 - Architect(also scrum lead) - design and mentoring the team
 - 3 server side developer - java, caching, redis and basic client knowledge
 - 1 UI developer - angular, react js
