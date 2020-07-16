package com.yuschool.bean;

import com.yuschool.annotation.CreatedTime;
import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;

public class FollowRelation {
    private int id;
    private int userId;
    private int followingUserId;
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

    public int getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(int followingUserId) {
        this.followingUserId = followingUserId;
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
        return "FollowRelation{" +
                "id=" + id +
                ", userId=" + userId +
                ", followingUserId=" + followingUserId +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
