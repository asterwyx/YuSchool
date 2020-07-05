package com.shareknowledge.bean;

import java.sql.Timestamp;

import static com.shareknowledge.utils.MyConstants.PK_NULL;

public class Notice extends Entity{
    private int senderId;
    private Timestamp sendTime;
    private String detail;

    public Notice() {
        super();
        this.senderId = PK_NULL;
        this.sendTime = null;
        this.detail = null;
    }

    public Notice(
            int primaryKey,
            int senderId,
            Timestamp sendTime,
            String detail
    ) {
        super(primaryKey);
        this.senderId = senderId;
        this.sendTime = sendTime;
        this.detail = detail;
    }

    public int getSenderId() { return senderId; }

    public void setSenderId(int senderId) { this.senderId = senderId; }

    public Timestamp getSendTime() { return sendTime; }

    public void setSendTime(Timestamp sendTime) { this.sendTime = sendTime; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

}
