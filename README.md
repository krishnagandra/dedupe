Deduplication in streaming data using Flink

Check blog post to understand more on deduplication. https://medium.com/@krishnagandra7/6249ce10a96b

To run this application, create two topics in Kafka, one topic to publish duplicate data and other one to push data after deduplication.

kafka-topics --create --topic sensor-data-incoming  --bootstrap-server localhost:9092

kafka-topics --create --topic sensor-data-out  --bootstrap-server localhost:9092

Sample data:

time,power,temp,humidity,light,CO2,dust

"2015-08-01 00:00:28",0,32,40,0,973,27.18

"2015-08-01 00:00:58",0,32,40,0,973,27.19

"2015-08-01 00:01:28",0,32,40,0,973,24.5

"2015-08-01 00:01:58",0,32,40,0,973,38.43

"2015-08-01 00:02:28",0,32,40,0,973,37.58

"2015-08-01 00:02:59",0,32,40,0,971,19.35

"2015-08-01 00:03:29",0,32,40,0,971,36.46

"2015-08-01 00:03:59",0,32,40,0,971,33.35

"2015-08-01 00:04:29",0,32,40,0,973,31.67

Created composite key to remove records which has  same (humidity, temp, CO2,light. 

Output topic data:

{"time":"2015-08-01 00:02:28","power":"0","temp":"32","humidity":"40","light":"0","dust":"27.58","compositeKey":"32:40:973:0","co2":"973"}

{"time":"2015-08-01 00:02:59","power":"0","temp":"32","humidity":"40","light":"0","dust":"29.35","compositeKey":"32:40:971:0","co2":"971"}



assuming kafka running on localhost:9092, otherwise change the url in App.java.

We can directly run the app in intellij or if you have flink running outside use below url to run it on cluster.

flink run -c com.pipelineworks.stream.App  "path to dedupe-1.0-SNAPSHOT.jar"




