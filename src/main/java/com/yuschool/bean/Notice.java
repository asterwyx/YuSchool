package com.yuschool.bean;

import java.sql.Timestamp;


public class Notice {

    private int id;
    private int senderId;
    private String detail;
    private Timestamp sentTime;
    private Timestamp lastUpdatedTime;

    public int getSenderId() { return senderId; }

    public void setSenderId(int senderId) { this.senderId = senderId; }

    public Timestamp getSentTime() { return sentTime; }

    public void setSentTime(Timestamp sentTime) { this.sentTime = sentTime; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
