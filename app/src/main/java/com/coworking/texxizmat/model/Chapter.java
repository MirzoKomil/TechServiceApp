package com.coworking.texxizmat.model;

import java.util.ArrayList;
import java.util.List;

public class Chapter {

    private int id; // Add an ID field
    private String chapterName;
    private List<Topic> topicsList = new ArrayList<>();

    public Chapter(int id, String chapterName, List<Topic> topicsList) {
        this.id = id;
        this.chapterName = chapterName;
        this.topicsList = topicsList;
    }

    public int getId() {
        return id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public List<Topic> getTopicsList() {
        return topicsList;
    }

    public void setTopicsList(List<Topic> topicsList) {
        this.topicsList = topicsList;
    }
}


