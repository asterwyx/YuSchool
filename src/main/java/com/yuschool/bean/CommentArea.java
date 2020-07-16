package com.yuschool.bean;


import com.yuschool.annotation.CreatedTime;
import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;

public class CommentArea {

    private int id;
    private String commentAreaName;
    private int commentAreaType;
    private int courseId;
    @CreatedTime
    private Timestamp createdTime;
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public String getCommentAreaName() {
        return commentAreaName;
    }

    public void setCommentAreaName(String commentAreaName) {
        this.commentAreaName = commentAreaName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCommentAreaType() {
        return commentAreaType;
    }

    public void setCommentAreaType(int commentAreaType) {
        this.commentAreaType = commentAreaType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "CommentArea{" +
                "id=" + id +
                ", commentAreaName='" + commentAreaName + '\'' +
                ", commentAreaType=" + commentAreaType +
                ", courseId=" + courseId +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
