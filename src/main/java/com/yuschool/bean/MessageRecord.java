package com.yuschool.bean;

import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;

public class MessageRecord {

    private int id;
    private int messageId;
    private int receiverId;
    private boolean isRead;
    @UpdatedTime
    private Timestamp lastUpdatedTime;



    public int getId() {
        return id;
    }

    public MessageRecord setId(int id) {
        this.id = id;
        return this;
    }

    public int getMessageId() {
        return messageId;
    }

    public MessageRecord setMessageId(int messageId) {
        this.messageId = messageId;
        return this;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public MessageRecord setReceiverId(int receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    public boolean isRead() {
        return isRead;
    }

    public MessageRecord setRead(boolean read) {
        isRead = read;
        return this;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public MessageRecord setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
        return this;
    }
}
