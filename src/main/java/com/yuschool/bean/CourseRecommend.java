package com.yuschool.bean;


import com.yuschool.annotation.UpdatedTime;

import java.sql.Timestamp;

public class CourseRecommend {

    private int courseId;
    private int recommended;
    @UpdatedTime
    private Timestamp lastUpdatedTime;

    public int getCourseId() { return courseId; }

    public void setCourseId(int courseId) { this.courseId = courseId; }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "CourseRecommend{" +
                "courseId=" + courseId +
                ", recommended=" + recommended +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
