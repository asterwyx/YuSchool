package com.yuschool.bean;

import com.yuschool.bean.factory.AuthorityFactory;
import com.yuschool.annotation.UpdatedTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 存储用户账号的实体，非自增主键，使用user_id作为主键并和用户进行关联
 */
public class Account extends User {

    // 对应用户基本信息的id
    private int userId;
    // 用户绑定的电话号码
    private String phoneNum;
    // 账户信息上一次更新的时间
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public Account() {
        this("111", "", Collections.singletonList(AuthorityFactory.getInstanceByName(Authority.ROLE_USER)));
    }

    public Account(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Account(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
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
}
