package com.yuschool.bean;

import com.yuschool.annotation.CreatedTime;
import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;

public class Plan {
    private int courseId;
    private String plan;
    @CreatedTime
    private Timestamp createdTime;
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "courseId=" + courseId +
                ", plan='" + plan + '\'' +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
