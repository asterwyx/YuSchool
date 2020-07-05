package com.shareknowledge.bean;

import java.sql.Timestamp;

import static com.shareknowledge.utils.MyConstants.FLAG_NULL;
import static com.shareknowledge.utils.MyConstants.PK_NULL;

public class Message extends Entity{
    private int senderId;
    private int receiverId;
    private Timestamp sendTime;
    private String detail;
    private int read;

    public Message() {
        super();
        this.senderId = PK_NULL;
        this.receiverId = PK_NULL;
        this.sendTime = null;
        this.detail = null;
        this.read = FLAG_NULL;
    }

    public Message(
            int primaryKey,
            int senderId,
            int receiverId,
            Timestamp sendTime,
            String detail,
            int read
    ) {
        super(primaryKey);
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.sendTime = sendTime;
        this.detail = detail;
        this.read = read;
    }

    public int getSenderId() { return senderId; }

    public void setSenderId(int senderId) { this.senderId = senderId; }

    public int getReceiverId() { return receiverId; }

    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }

    public Timestamp getSendTime() { return sendTime; }

    public void setSendTime(Timestamp sendTime) { this.sendTime = sendTime; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public int getRead() { return read; }

    public void setRead(int read) { this.read = read; }
}
