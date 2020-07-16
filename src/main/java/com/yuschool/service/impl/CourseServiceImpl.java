package com.yuschool.service.impl;

import com.yuschool.bean.Course;
import com.yuschool.bean.UserCourseRelation;
import com.yuschool.constants.enums.Operation;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.CourseMapper;
import com.yuschool.mapper.UserCourseRelationMapper;
import com.yuschool.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    public static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    UserCourseRelationMapper userCourseRelationMapper;

    @Override
    public List<Course> getAllStarCourses(int userId) {
        return this.getCoursesByIds(userCourseRelationMapper.selectAllStarsByUserId(userId));
    }

    @Override
    public RetCode updateStarCourse(int useId, int courseId, Operation operation) {
        return null;
    }

    @Override
    public List<Course> getAllOwnCourses(int userId) {
        return this.getCoursesByIds(userCourseRelationMapper.selectAllOwnsByUserId(userId));
    }

    @Override
    public RetCode publishCourse(int userId, Course course, String plan) {
        return null;
    }

    @Override
    public RetCode deletePubCourse(int userId, int courseId) {
        return null;
    }

    @Override
    public List<Course> getAllManageCourses(int userId) {
        return this.getCoursesByIds(userCourseRelationMapper.selectAllManagesByUserId(userId));
    }

    @Override
    public RetCode updateManageCourse(int userId, int courseId, Operation operation) {
        return null;
    }

    @Override
    public boolean checkStar(int userId, int courseId) {
        UserCourseRelation relation = userCourseRelationMapper.selectBy2Id(userId, courseId);
        if (relation == null) {
            return false;
        } else {
            return relation.isHasStarred();
        }
    }

    @Override
    public boolean checkOwn(int userId, int courseId) {
        UserCourseRelation relation = userCourseRelationMapper.selectBy2Id(userId, courseId);
        if (relation == null) {
            return false;
        } else {
            return relation.isOwns();
        }

    }

    @Override
    public boolean checkManage(int userId, int courseId) {
        UserCourseRelation relation = userCourseRelationMapper.selectBy2Id(userId, courseId);
        if (relation == null) {
            return false;
        } else {
            return relation.isManages();
        }

    }

    @Override
    public List<Course> getAllCourses() {
        return courseMapper.selectAll();
    }

    List<Course> getCoursesByIds(List<Integer> ids) {
        List<Course> courses = new ArrayList<>();
        for (int id : ids) {
            Course course = courseMapper.selectById(id);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }
}
