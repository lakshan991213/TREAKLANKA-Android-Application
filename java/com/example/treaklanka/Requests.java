package com.example.treaklanka;

import android.widget.EditText;

public class Requests {
    String nameTV, emailTV, contactTV, startDateTV, endDateTV, startTimeTV, noOfVisitorsTV, travelModeTV, commentsTV;

    public Requests(String nameTV, String emailTV, String contactTV, String startDateTV, String endDateTV, String startTimeTV, String noOfVisitorsTV, String travelModeTV, String commentsTV) {
        this.nameTV = nameTV;
        this.emailTV = emailTV;
        this.contactTV = contactTV;
        this.startDateTV = startDateTV;
        this.endDateTV = endDateTV;
        this.startTimeTV = startTimeTV;
        this.noOfVisitorsTV = noOfVisitorsTV;
        this.travelModeTV = travelModeTV;
        this.commentsTV = commentsTV;
    }

    public String getNameTV() {
        return nameTV;
    }

    public String getEmailTV() {
        return emailTV;
    }

    public String getContactTV() {
        return contactTV;
    }

    public String getStartDateTV() {
        return startDateTV;
    }

    public String getEndDateTV() {
        return endDateTV;
    }

    public String getStartTimeTV() {
        return startTimeTV;
    }

    public String getNoOfVisitorsTV() {
        return noOfVisitorsTV;
    }

    public String getTravelModeTV() {
        return travelModeTV;
    }

    public String getCommentsTV() {
        return commentsTV;
    }
}
