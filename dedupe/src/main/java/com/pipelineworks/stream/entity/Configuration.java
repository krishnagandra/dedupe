package com.pipelineworks.stream.entity;

public final class Configuration {

    private String inputTopic;
    private String outputTopic;
    private String consumerGroup;
    private String bootstrapServers;

    public Configuration() {
    }

    public Configuration(String inputTopic, String outputTopic, String consumerGroup, String bootstrapServers) {
        this.inputTopic = inputTopic;
        this.outputTopic = outputTopic;
        this.consumerGroup = consumerGroup;
        this.bootstrapServers = bootstrapServers;
    }

    public String getInputTopic() {
        return inputTopic;
    }

    public void setInputTopic(String inputTopic) {
        this.inputTopic = inputTopic;
    }

    public String getOutputTopic() {
        return outputTopic;
    }

    public void setOutputTopic(String outputTopic) {
        this.outputTopic = outputTopic;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }
}
