package com.shareknowledge.bean;


import static com.shareknowledge.utils.MyConstants.AUTHORITY_USER;
import static com.shareknowledge.utils.MyConstants.PK_NULL;

public class Authority extends Entity{

    private int userId;

    private int authority;

    public Authority() { this(PK_NULL,PK_NULL,AUTHORITY_USER); }

    public Authority(
            int primaryKey,
            int userId,
            int authority)
    {
        super(primaryKey);
        this.userId = userId;
        this.authority = authority;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
