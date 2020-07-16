package com.yuschool.bean;

import com.yuschool.annotation.CreatedTime;
import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;

public class UserCourseRelation {
    private int id;
    private int userId;
    private int courseId;
    private boolean hasStarred;
    private boolean owns;
    private boolean manages;
    @CreatedTime
    private Timestamp createdTime;
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isHasStarred() {
        return hasStarred;
    }

    public void setHasStarred(boolean hasStarred) {
        this.hasStarred = hasStarred;
    }

    public boolean isOwns() {
        return owns;
    }

    public void setOwns(boolean owns) {
        this.owns = owns;
    }

    public boolean isManages() {
        return manages;
    }

    public void setManages(boolean manages) {
        this.manages = manages;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "UserCourseRelation{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", hasStarred=" + hasStarred +
                ", owns=" + owns +
                ", manages=" + manages +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
