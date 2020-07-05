package com.shareknowledge.bean;

import com.shareknowledge.utils.MyConstants;

public class Resource extends Entity {
    private String resourceName;
    private String fullName;
    private String type;
    private int size;

    public Resource(
            int primaryKey,
            String resourceName,
            String fullName,
            String type,
            int size
    ) {
        super(primaryKey);
        this.resourceName = resourceName;
        this.fullName = fullName;
        this.type = type;
        this.size = size;
    }

    public Resource() {
        this(MyConstants.PK_NULL, "", "", "", MyConstants.NUM_NULL);
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
