package com.yuschool.service;

import com.yuschool.bean.Course;
import com.yuschool.constants.enums.Operation;
import com.yuschool.constants.enums.RetCode;

import java.util.List;

public interface CourseService {
    boolean addCourseRecord(Course prepCourse);
    boolean deleteCourseRecord(int courseId);
    boolean commitCourse(int courseId);
    List<Course> getAllStarCourses(int userId);
    List<Course> getAllOwnCourses(int userId);
    RetCode publishCourse(int userId, Course course, String plan);
    boolean deleteCourse(int courseId);
    List<Course> getAllManageCourses(int userId);
    RetCode updateStarCourse(int userId, int courseId, Operation operation);
    RetCode updateManageCourse(int userId, int courseId, Operation operation);
    RetCode updateOwnCourse(int userId, int courseId, Operation operation);
    boolean checkStar(int userId, int courseId);
    boolean checkOwn(int userId, int courseId);
    boolean checkManage(int userId, int courseId);
    List<Course> getAllCourses();
    List<Course> getCoursesByPage(int page, int size);
    List<Course> getCoursesByIds(List<Integer> ids);
    RetCode deleteCoursesByIds(List<Integer> ids);
    RetCode deletePubCourses(List<Integer> ids);
}
