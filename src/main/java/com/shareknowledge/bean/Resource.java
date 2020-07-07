package com.shareknowledge.bean;

import java.sql.Timestamp;

public class Resource {

    private int id;
    private String resourceName;
    private String fullName;
    private String type;
    private int size;
    private int sectionId;
    private Timestamp lastUpdatedTime;

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

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", resourceName='" + resourceName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", sectionId=" + sectionId +
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
