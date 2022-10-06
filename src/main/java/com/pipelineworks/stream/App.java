package com.pipelineworks.stream;

import com.pipelineworks.stream.process.StreamProcess;

/**
 * dedupe stream data.
 *
 */
public class App 
{

    public static void main(String[] args){

        try {
            StreamProcess sStream = new StreamProcess();
            String inputTopic = "sensor-data-incoming";
            String outputTopic = "sensor-data-out";
            String consumerGroup = "consumer-group1";
            String bootstrapServers = "localhost:9092";

            sStream.processData(inputTopic,outputTopic, consumerGroup, bootstrapServers );
        } catch (Exception ex){
            System.out.println( "  stream error message " + ex.getMessage());
        }
    }

}
