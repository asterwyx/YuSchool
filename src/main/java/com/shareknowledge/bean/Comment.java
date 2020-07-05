package com.shareknowledge.bean;

import java.sql.Timestamp;

import static com.shareknowledge.utils.MyConstants.PK_NULL;

public class Comment extends  Entity{

    private String detail;
    private Timestamp publishTime;
    private int likes;
    private int dislikes;
    private String replyList;
    private int commentAreaId;
    private int userId;

    public Comment() {
        this(PK_NULL, "", new Timestamp(System.currentTimeMillis()), 0, 0, "", PK_NULL,PK_NULL);
    }

    public Comment(
            int primaryKey,
            String detail,
            Timestamp publishTime,
            int likes,
            int dislikes,
            String replyList,
            int commentAreaId,
            int userId
    ) {
        super(primaryKey);
        this.detail = detail;
        this.publishTime = publishTime;
        this.likes = likes;
        this.dislikes = dislikes;
        this.replyList = replyList;
        this.commentAreaId = commentAreaId;
        this.userId=userId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
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

    public String getReplyList() {
        return replyList;
    }

    public void setReplyList(String replyList) {
        this.replyList = replyList;
    }

    public int getCommentAreaId() {
        return commentAreaId;
    }

    public void setCommentAreaId(int commentAreaId) {
        this.commentAreaId = commentAreaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

