package com.pipelineworks.stream.util;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

public class KafkaUtils {

    public static FlinkKafkaConsumer<String> createStringConsumerForTopic(
            String topic, String kafkaAddress, String kafkaGroup ) {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", kafkaAddress);
        props.setProperty("group.id",kafkaGroup);
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(
                topic, new SimpleStringSchema(), props);

        return consumer;
    }

    public static FlinkKafkaProducer<String> createStringProducer(
            String topic, String kafkaAddress){

        return new FlinkKafkaProducer<>(kafkaAddress,
                topic, new SimpleStringSchema());
    }


}
