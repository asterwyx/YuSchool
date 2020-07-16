package com.yuschool.bean;

import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;


public class Message {

    private int id;
    private int senderId;
    private int receiverId;
    private Timestamp sentTime;
    private String detail;
    private int isRead;
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public int getSenderId() { return senderId; }

    public void setSenderId(int senderId) { this.senderId = senderId; }

    public int getReceiverId() { return receiverId; }

    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }

    public Timestamp getSentTime() { return sentTime; }

    public void setSentTime(Timestamp sentTime) { this.sentTime = sentTime; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", sentTime=" + sentTime +
                ", detail='" + detail + '\'' +
                ", is_read=" + isRead +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
