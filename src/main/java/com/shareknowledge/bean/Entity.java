package com.shareknowledge.bean;

import com.shareknowledge.utils.MyConstants;

import java.io.Serializable;

public class Entity implements Serializable {
    private int primaryKey;
    public Entity() {
        this.primaryKey = MyConstants.PK_NULL; // 初始化为空主键
    }

    public Entity(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getPrimaryKey() {
        return this.primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }
}
