package com.yuschool.bean;

import java.sql.Timestamp;


public class History {

    private int id;
    private Timestamp createdTime;
    private String detail;
    private int limit;
    private int userId;

    public Timestamp getCreatedTime() { return createdTime; }

    public void setCreatedTime(Timestamp createdTime) { this.createdTime = createdTime; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public int getLimit() { return limit; }

    public void setLimit(int limit) { this.limit = limit; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", detail='" + detail + '\'' +
                ", limit=" + limit +
                ", userId=" + userId +
                '}';
    }
}
