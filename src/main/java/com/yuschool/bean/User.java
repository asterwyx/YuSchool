package com.yuschool.bean;

import com.yuschool.annotation.CreatedTime;
import com.yuschool.annotation.UpdatedTime;
import com.yuschool.constants.DefaultValue;

import java.sql.Timestamp;
import java.util.StringJoiner;

import static com.yuschool.constants.DefaultValue.AGE_NULL;
import static com.yuschool.constants.DefaultValue.PK_NULL;

public class User {

    private int id;
    private String username;
    private String gender;
    private int age;
    private String headFilePath;
    private String detail;
    @CreatedTime
    private Timestamp registerTime;
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public User(
            int id,
            String username,
            String gender,
            int age,
            String headFilePath,
            String detail
    ) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.headFilePath = headFilePath;
        this.detail = detail;
        this.registerTime = new Timestamp(System.currentTimeMillis());
        this.lastUpdatedTime = new Timestamp(System.currentTimeMillis());
    }

    public User(String username) {
        this(PK_NULL, username, "", AGE_NULL, DefaultValue.DEFAULT_USER_HEAD_PATH, "");
    }

    public User() {
        this("");
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void clone(User user) {
        this.id = user.id;
        this.username = user.username;
        this.gender = user.gender;
        this.age = user.age;
        this.headFilePath = user.headFilePath;
        this.detail = user.detail;
        this.registerTime = user.registerTime;
        this.lastUpdatedTime = user.lastUpdatedTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username=" + username)
                .add("gender=" + gender)
                .add("age=" + age)
                .add("headFilePath=" + headFilePath)
                .add("detail=" + detail)
                .add("registerTime=" + registerTime)
                .add("lastUpdatedTime=" + lastUpdatedTime)
                .toString();
    }
}
