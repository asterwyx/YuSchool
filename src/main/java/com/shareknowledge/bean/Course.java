package com.shareknowledge.bean;

import java.sql.Timestamp;

public class Course extends Entity{
    public static final String COURSE_ROOT = "/static/image/classes/";
    private String courseName;
    private Timestamp courseTime;
    private String introduction;
    private String coverFilePath;

    public Course() {
        super();
        this.courseName = null;
        this.courseTime = null;
        this.introduction = null;
        this.coverFilePath = null;
    }

    public Course(
            int primaryKey,
            String courseName,
            Timestamp courseTime,
            String introduction,
            String coverFilePath
    ) {
        super(primaryKey);
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.introduction = introduction;
        this.coverFilePath = coverFilePath;
    }

    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Timestamp getCourseTime() { return courseTime; }

    public void setCourseTime(Timestamp courseTime) { this.courseTime = courseTime; }

    public String getIntroduction() { return introduction; }

    public void setIntroduction(String introduction) { this.introduction = introduction; }

    public String getCoverFilePath() { return coverFilePath; }

    public void setCoverFilePath(String coverFilePath) { this.coverFilePath = coverFilePath; }
}
