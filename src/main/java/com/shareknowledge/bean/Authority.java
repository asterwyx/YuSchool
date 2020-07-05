package com.shareknowledge.bean;

import java.sql.Timestamp;

public class Authority extends Entity{

    private int userId;
    private int authority;
    private Timestamp lastUpdatedTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "userId=" + userId +
                ", authority=" + authority +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
