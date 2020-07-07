package com.shareknowledge.bean;

import java.sql.Timestamp;

public class User {

    private int id;
    private String userName;
    private String gender;
    private int age;
    private String headFilePath;
    private String detail;
    private Timestamp registerTime;
    private Timestamp lastUpdatedTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHeadFilePath() {
        return headFilePath;
    }

    public void setHeadFilePath(String headFilePath) {
        this.headFilePath = headFilePath;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", headFilePath='" + headFilePath + '\'' +
                ", detail='" + detail + '\'' +
                ", registerTime=" + registerTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
