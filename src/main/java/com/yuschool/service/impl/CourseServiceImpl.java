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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.yuschool.constants.enums.RetCode.*;

@Service
public class CourseServiceImpl implements CourseService {

    public static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseMapper courseMapper;
    private final UserCourseRelationMapper userCourseRelationMapper;

    public CourseServiceImpl(CourseMapper courseMapper, UserCourseRelationMapper userCourseRelationMapper) {
        this.courseMapper = courseMapper;
        this.userCourseRelationMapper = userCourseRelationMapper;
    }

    @Override
    public boolean addCourseRecord(Course prepCourse) {
        int infNum = courseMapper.insert(prepCourse);
        if (infNum <= 0) {
            logger.error("插入课程失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCourseRecord(int courseId) {
        int infNum = courseMapper.deleteCourseById(courseId);
        if (infNum <= 0) {
            logger.error("删除课程记录失败");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean commitCourse(int courseId) {
        int infNum = courseMapper.updateReviewStatus(courseId, false);
        if (infNum <= 0) {
            logger.error("设置课程未审批失败");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Course> getAllStarCourses(int userId) {
        return this.getCoursesByIds(userCourseRelationMapper.selectAllStarsByUserId(userId));
    }

    @Override
    public RetCode updateStarCourse(int userId, int courseId, Operation operation) {
        RetCode rc = SUCCESS;
        UserCourseRelation relation = userCourseRelationMapper.selectBy2Id(userId, courseId);
        switch (operation) {
            case OP_ADD:
            {
                if (relation == null) {
                    relation = new UserCourseRelation();
                    relation.setUserId(userId);
                    relation.setCourseId(courseId);
                    relation.setHasStarred(true);
                    relation.setOwns(false);
                    relation.setManages(false);
                    int infNum = userCourseRelationMapper.insert(relation);
                    if (infNum <= 0) {
                        logger.error("插入用户课程关系失败");
                        rc = FAIL_OP;
                    }
                } else {
                    if (relation.isHasStarred()) {
                        logger.error("重复收藏");
                        rc = DUP_VALUE;
                    } else {
                        relation.setHasStarred(true);
                        int infNum = userCourseRelationMapper.update(relation);
                        if (infNum <= 0) {
                            logger.error("更新用户收藏课程失败");
                            rc = FAIL_OP;
                        }
                    }
                }
                break;
            }
            case OP_DEL:
            {
                if (relation == null || !relation.isHasStarred()) {
                    logger.error("用户未收藏该课程");
                    rc = WRONG_OP;
                } else {
                    relation.setHasStarred(true);
                    int infNum = userCourseRelationMapper.update(relation);
                    if (infNum <= 0) {
                        logger.error("更新用户收藏课程失败");
                        rc = FAIL_OP;
                    }
                }
                break;
            }
        }
        return rc;
    }

    @Override
    public List<Course> getAllOwnCourses(int userId) {
        return this.getCoursesByIds(userCourseRelationMapper.selectAllOwnsByUserId(userId));
    }

    @Override
    public RetCode publishCourse(int userId, Course course, String plan) {
        // TODO
        return null;
    }

    @Override
    public boolean deleteCourse(int courseId) {
        int infNum = courseMapper.deleteCourseById(courseId);
        return false;
    }

    @Override
    public List<Course> getAllManageCourses(int userId) {
        return this.getCoursesByIds(userCourseRelationMapper.selectAllManagesByUserId(userId));
    }

    @Override
    public RetCode updateManageCourse(int userId, int courseId, Operation operation) {
        RetCode rc = SUCCESS;
        UserCourseRelation relation = userCourseRelationMapper.selectBy2Id(userId, courseId);
        switch (operation) {
            case OP_ADD:
            {
                if (relation == null) {
                    relation = new UserCourseRelation();
                    relation.setUserId(userId);
                    relation.setCourseId(courseId);
                    relation.setHasStarred(false);
                    relation.setOwns(false);
                    relation.setManages(true);
                    int infNum = userCourseRelationMapper.insert(relation);
                    if (infNum <= 0) {
                        logger.error("插入用户课程关系失败");
                        rc = FAIL_OP;
                    }
                } else {
                    if (relation.isManages()) {
                        logger.error("重复添加管理");
                        rc = DUP_VALUE;
                    } else {
                        relation.setManages(true);
                        int infNum = userCourseRelationMapper.update(relation);
                        if (infNum <= 0) {
                            logger.error("更新用户收藏课程失败");
                            rc = FAIL_OP;
                        }
                    }
                }
                break;
            }
            case OP_DEL:
            {
                if (relation == null || !relation.isManages()) {
                    logger.error("用户未管理该课程");
                    rc = WRONG_OP;
                } else {
                    relation.setManages(true);
                    int infNum = userCourseRelationMapper.update(relation);
                    if (infNum <= 0) {
                        logger.error("更新用户收藏课程失败");
                        rc = FAIL_OP;
                    }
                }
                break;
            }
        }
        return rc;
    }

    @Override
    public RetCode updateOwnCourse(int userId, int courseId, Operation operation) {
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

    @Override
    public List<Course> getCoursesByPage(int page, int size) {
        return courseMapper.selectByPage((page - 1) * size, size);
    }

    @Override
    public List<Course> getCoursesByIds(List<Integer> ids) {
        List<Course> courses = new ArrayList<>();
        for (int id : ids) {
            Course course = courseMapper.selectById(id);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public RetCode deleteCoursesByIds(List<Integer> ids) {
        int infNum = courseMapper.deleteCoursesByIds(ids);
        if (infNum == ids.size()) {
            return SUCCESS;
        } else if (infNum < ids.size()) {
            return INCOMPLETE_OP;
        } else {
            return WRONG_OP;
        }
    }

    @Override
    public RetCode deletePubCourses(List<Integer> ids) {
        int delCnter = 0;
        for (int id : ids) {
            boolean status = this.deleteCourse(id);
            if (status) {
                ++delCnter;
            }
        }
        if (delCnter == ids.size()) {
            return SUCCESS;
        } else {
            return INCOMPLETE_OP;
        }
    }
}
