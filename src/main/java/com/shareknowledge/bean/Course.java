package com.shareknowledge.bean;

import java.sql.Timestamp;

public class Course {

    private int id;
    private String courseName;
    private String introduction;
    private String coverFilePath;
    private int starNum;
    private Timestamp createdTime;
    private Timestamp lastUpdatedTime;


    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Timestamp getCreatedTime() { return createdTime; }

    public void setCreatedTime(Timestamp createdTime) { this.createdTime = createdTime; }

    public String getIntroduction() { return introduction; }

    public void setIntroduction(String introduction) { this.introduction = introduction; }

    public String getCoverFilePath() { return coverFilePath; }

    public void setCoverFilePath(String coverFilePath) { this.coverFilePath = coverFilePath; }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", coverFilePath='" + coverFilePath + '\'' +
                ", starNum=" + starNum +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
