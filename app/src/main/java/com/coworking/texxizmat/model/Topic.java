package com.coworking.texxizmat.model;

public class Topic {

    String topicName;
    String fileName;

    public Topic(String topicName, String fileName) {
        this.topicName = topicName;
        this.fileName = fileName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
