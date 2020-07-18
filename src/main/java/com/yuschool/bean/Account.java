package com.yuschool.bean;

import com.yuschool.annotation.UpdatedTime;
import com.yuschool.constants.DefaultValue;

import java.sql.Timestamp;

/**
 * 存储用户账号的实体，非自增主键，使用user_id作为主键并和用户进行关联
 */
public class Account {

    private int userId;
    private String username;
    private String password;
    private String phoneNum;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public Account(
            int userId,
            String username,
            String password,
            String phoneNum,
            boolean accountNonExpired,
            boolean accountNonLocked,
            boolean credentialsNonExpired,
            boolean enabled
    ) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.lastUpdatedTime = new Timestamp(System.currentTimeMillis());
    }

    public Account(String username, String password) {
        this(DefaultValue.PK_NULL, username, password, "", true, true, true, true);
    }

    public Account() {
        this("", "");
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", phoneNum='" + phoneNum + '\'' +
                ", lastUpdatedTime=" + lastUpdatedTime +
                "} " + super.toString();
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
