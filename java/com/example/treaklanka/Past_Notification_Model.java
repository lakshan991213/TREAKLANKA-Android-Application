package com.example.treaklanka;

public class Past_Notification_Model {
    String title,description;

    public Past_Notification_Model() {
    }

    public Past_Notification_Model(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
