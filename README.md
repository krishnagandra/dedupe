Deduplication in streaming data using Flink

Check blog post to understand more on deduplication. https://medium.com/@krishnagandra7/6249ce10a96b

To run this application, create two topics in Kafka, one topic to push duplicate data and other one to push data after deduplication.

kafka-topics --create --topic sensor-data-incoming  --bootstrap-server localhost:9092

kafka-topics --create --topic sensor-data-out  --bootstrap-server localhost:9092


Assuming kafka running on localhost:9092, otherwise change the url in App.java.

We can directly run the app in intellij or if flink you have flink running outside use below url to run it.

flink run -c com.pipelineworks.stream.App  "path to dedupe-1.0-SNAPSHOT.jar"




