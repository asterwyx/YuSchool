package com.shareknowledge.bean;

import java.sql.Timestamp;

public class Comment {

    private int id;
    private String detail;
    private int likes;
    private int dislikes;
    private int publisherId;
    private int commentAreaId;
    private Timestamp publishedTime;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Timestamp getPublishTime() {
        return publishedTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishedTime = publishTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getCommentAreaId() {
        return commentAreaId;
    }

    public void setCommentAreaId(int commentAreaId) {
        this.commentAreaId = commentAreaId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", publisherId=" + publisherId +
                ", commentAreaId=" + commentAreaId +
                ", publishedTime=" + publishedTime +
                '}';
    }
}
