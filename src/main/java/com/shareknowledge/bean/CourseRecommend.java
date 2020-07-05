package com.shareknowledge.bean;

import static com.shareknowledge.utils.MyConstants.PK_NULL;

public class CourseRecommend extends Entity{

    private int courseId;

    private boolean recommend;

    private Course course;

    public CourseRecommend() { this(PK_NULL,PK_NULL,false); }

    public CourseRecommend(
            int primaryKey,
            int courseId,
            boolean recommend)
    {
        super(primaryKey);
        this.courseId = courseId;
        this.recommend = recommend;
    }

    public int getCourseId() { return courseId; }

    public void setCourseId(int courseId) { this.courseId = courseId; }

    public boolean isRecommend() { return recommend; }

    public void setRecommend(boolean recommend) { this.recommend = recommend; }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
