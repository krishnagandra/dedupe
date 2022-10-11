package com.pipelineworks.stream;

import com.pipelineworks.stream.entity.Configuration;
import com.pipelineworks.stream.process.StreamProcess;
import com.pipelineworks.stream.util.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * dedupe stream data.
 *
 */
public class App 
{

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args){

        try {
            StreamProcess sStream = new StreamProcess();

            if( args.length != 1 ) {
                LOG.info("Configuration file path is required");
                return;
            }

            Configuration  config = ConfigUtils.getConfiguration(args[0]);
            sStream.processData(config.getInputTopic(),config.getOutputTopic(), config.getConsumerGroup(), config.getBootstrapServers() );
        } catch (Exception ex){
            LOG.info( "  stream error message " + ex.getMessage());
        }
    }

}
