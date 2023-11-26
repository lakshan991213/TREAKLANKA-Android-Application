package com.example.treaklanka;

public class Notification_Model {
    String title,description;

    public Notification_Model(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Notification_Model() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
