package com.yuschool.bean;

import com.yuschool.annotation.CreatedTime;
import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;
import java.util.StringJoiner;


public class Message {

    private int id;
    private int senderId;
    private String detail;
    private int refCnt;
    @CreatedTime
    private Timestamp sentTime;
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public int getSenderId() { return senderId; }

    public void setSenderId(int senderId) { this.senderId = senderId; }

    public Timestamp getSentTime() { return sentTime; }

    public void setSentTime(Timestamp sentTime) { this.sentTime = sentTime; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public int getRefCnt() {
        return refCnt;
    }

    public void setRefCnt(int refCnt) {
        this.refCnt = refCnt;
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
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("senderId=" + senderId)
                .add("detail=" + detail)
                .add("refCnt=" + refCnt)
                .add("sentTime=" + sentTime)
                .add("lastUpdatedTime=" + lastUpdatedTime)
                .toString();
    }
}
