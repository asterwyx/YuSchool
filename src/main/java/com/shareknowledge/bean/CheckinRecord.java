package com.shareknowledge.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 存储用户签到天数的实体，非自增主键，主键即是用户id
 */
public class CheckinRecord implements Serializable {

    private int userId;
    private int checkinDays;
    private int points;
    private Timestamp lastCheckinTime;

    public int getCheckinDays() {
        return checkinDays;
    }

    public void setCheckinDays(int checkinDays) {
        this.checkinDays = checkinDays;
    }

    public Timestamp getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(Timestamp lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CheckinRecord{" +
                "userId=" + userId +
                ", checkinDays=" + checkinDays +
                ", points=" + points +
                ", lastCheckinTime=" + lastCheckinTime +
                '}';
    }
}
