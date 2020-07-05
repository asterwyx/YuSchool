package com.shareknowledge.bean;

import java.util.Vector;

import static com.shareknowledge.utils.MyConstants.PK_NULL;
import static com.shareknowledge.utils.MyConstants.TYPE_ASSESS;

public class CommentArea extends Entity{

    private String commentAreaName;
    private int commentAreaType;
    private int courseId;

    private Vector<Comment> commentVec;

    public CommentArea() {
        this(PK_NULL, "", TYPE_ASSESS, PK_NULL);
    }

    public CommentArea(
            int primaryKey,
            String commentAreaName,
            int commentAreaType,
            int courseId
    ) {
        super(primaryKey);
        this.commentAreaName = commentAreaName;
        this.commentAreaType = commentAreaType;
        this.courseId = courseId;
    }

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
}
