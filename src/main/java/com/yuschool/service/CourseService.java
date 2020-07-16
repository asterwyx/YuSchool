package com.yuschool.service;

import com.yuschool.bean.Course;
import com.yuschool.constants.enums.Operation;
import com.yuschool.constants.enums.RetCode;

import java.util.List;

public interface CourseService {

    List<Course> getAllStarCourses(int userId);

    RetCode updateStarCourse(int userId, int courseId, Operation operation);

    List<Course> getAllOwnCourses(int userId);

    RetCode publishCourse(int userId, Course course, String plan);

    RetCode deletePubCourse(int userId, int courseId);

    List<Course> getAllManageCourses(int userId);

    RetCode updateManageCourse(int userId, int courseId, Operation operation);

    boolean checkStar(int userId, int courseId);

    boolean checkOwn(int userId, int courseId);

    boolean checkManage(int userId, int courseId);

    List<Course> getAllCourses();

}
