package com.yuschool.bean;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private int id;
    private String authority;


    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 权限常量
     */
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
}
