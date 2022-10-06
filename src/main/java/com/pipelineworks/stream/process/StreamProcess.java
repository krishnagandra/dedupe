package com.pipelineworks.stream.process;

import com.pipelineworks.stream.entity.SensorData;
import com.pipelineworks.stream.util.KafkaUtils;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.contrib.streaming.state.EmbeddedRocksDBStateBackend;
import org.apache.flink.runtime.state.storage.FileSystemCheckpointStorage;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamProcess {

    private static final Logger LOG = LoggerFactory.getLogger(StreamProcess.class);


    public void processData(String inputTopic , String outputTopic, String consumerGroup, String bootstrapServers  ) throws Exception {


        StreamExecutionEnvironment environment = StreamExecutionEnvironment
                .getExecutionEnvironment();

        environment.enableCheckpointing(100000);


        // to  use rocksdb state  store.
/*
        environment.setStateBackend(new EmbeddedRocksDBStateBackend());
        environment.getCheckpointConfig().setCheckpointStorage("file:///target/tmp/rocksdb");
        environment.getCheckpointConfig().setCheckpointStorage(new FileSystemCheckpointStorage("file:///target/tmp/rocksdb"));
*/

        FlinkKafkaConsumer<String> flinkKafkaConsumer = KafkaUtils.
                createStringConsumerForTopic(inputTopic, bootstrapServers, consumerGroup);

        DataStream<SensorData> inputStream = environment
                .addSource(flinkKafkaConsumer).map(new MapToClass());

        DataStream<String> outputStream = inputStream
                .keyBy(SensorData::getCompositeKey)
                .flatMap(new DataDedupe());

        FlinkKafkaProducer<String> flinkKafkaProducer =
                KafkaUtils.createStringProducer(outputTopic, bootstrapServers);


        outputStream.addSink(flinkKafkaProducer);

        environment.executeAsync("process-sensor-data");

    }


    public static class DataDedupe extends RichFlatMapFunction<SensorData, String> {
        ValueState<Boolean> alreadySeen;

        @Override
        public void open(Configuration conf) {
            ValueStateDescriptor<Boolean> desc = new ValueStateDescriptor<>("alreadySeen", Types.BOOLEAN);
            alreadySeen = getRuntimeContext().getState(desc);
        }

        @Override
        public void flatMap(SensorData event, Collector<String> out) throws Exception {
            if (alreadySeen.value() == null) {
                out.collect(event.toString());
                alreadySeen.update(true);
            }
        }
    }


    public static final class MapToClass implements MapFunction<String, SensorData> {

        @Override
        public SensorData map(String s) throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(s, SensorData.class);
        }
    }

    public static final class MapToStrring implements MapFunction<SensorData, String> {

        @Override
        public String map(SensorData sensorData) throws Exception {
            return sensorData.toString();
        }
    }









}






