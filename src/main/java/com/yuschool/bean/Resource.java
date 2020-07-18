package com.yuschool.bean;

import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;

public class Resource {

    private int id;
    private String resourceName;
    private String fullName;
    private String type;
    private long size;
    private int courseId;
    @UpdatedTime
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", resourceName='" + resourceName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", sectionId=" + courseId +
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
