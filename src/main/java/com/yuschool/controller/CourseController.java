package com.yuschool.controller;

import com.sun.mail.imap.ResyncData;
import com.yuschool.bean.Course;
import com.yuschool.bean.Plan;
import com.yuschool.constants.ParamKey;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.service.CourseService;
import com.yuschool.service.PlanService;
import com.yuschool.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yuschool.constants.DefaultValue.PK_NULL;
import static com.yuschool.constants.ParamKey.*;
import static com.yuschool.constants.enums.RetCode.*;
import static com.yuschool.constants.enums.Operation.*;

@RequestMapping("/courses")
@RestController
public class CourseController {

    public static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;
    private final PlanService planService;

    public CourseController(CourseService courseService, PlanService planService) {
        this.courseService = courseService;
        this.planService = planService;
    }

    @GetMapping
    public Result getAllCourses(
            @RequestParam(value = P_PAGE, required = false, defaultValue = "-1") int page,
            @RequestParam(value = P_SIZE, required = false, defaultValue = "-1") int size
    ) {
        if (page == -1 || size == -1) {
            if (page == -1 && size == -1) {
                return Result.builder()
                        .data(courseService.getAllCourses())
                        .build();
            } else {
                return Result.withRetCode(WRONG_OP)
                        .message("参数必须同时有效或者不传参数")
                        .build();
            }
        } else {
            return Result.withRetCode(SUCCESS)
                    .data(courseService.getCoursesByPage(page, size))
                    .build();
        }
    }

    @PostMapping
    public Result uploadCourse(
            @RequestParam(P_COURSE_NAME) String courseName,
            @RequestParam(P_COURSE_INTRODUCTION) String introduction,
            @RequestParam(P_COURSE_COVER_PATH) String coverFilePath,
            @RequestParam(P_USER_ID) int userId
    ) {
        // 添加课程数据库记录
        Course course = new Course();
        course.setId(PK_NULL);
        course.setCourseName(courseName);
        course.setCoverFilePath(coverFilePath);
        course.setIntroduction(introduction);
        course.setStarNum(0);
        boolean status = courseService.addCourseRecord(course);
        if (!status) {
            logger.error("上传课程失败");
            return Result.withRetCode(FAIL_OP)
                    .message("上传课程失败")
                    .build();
        }
        // 添加用户创建课程记录
        RetCode ret = courseService.updateOwnCourse(userId, course.getId(), OP_ADD);
        if (ret != SUCCESS) {
            if (!courseService.deleteCourseRecord(course.getId())) {
                logger.error("上传课程事务不一致错误");
            }
        }
        return Result.withRetCode(ret)
                .message(ret == SUCCESS ? null : "添加用户创建课程记录失败")
                .build();
    }

    /**
     * 添加课程计划，初次添加的时候课程计划不会被暂存
     */
    @PostMapping("/{course_id}/plan")
    public Result uploadCoursePlan(
            @PathVariable("course_id") Integer courseId,
            @RequestParam(P_COURSE_PLAN) String plan
    ) {
        RetCode ret = planService.addPlan(courseId, plan);
        if (ret == SUCCESS) {
            return Result.withRetCode(SUCCESS).build();
        } else {
            return Result.withRetCode(ret).message("添加课程计划失败").build();
        }
    }

    @GetMapping("/{course_id}/plan")
    public Result getCoursePlan(
            @PathVariable("course_id") Integer courseId
    ) {
        Plan result = planService.getPlanByCourseId(courseId);
        if (result != null) {
            return Result.withRetCode(SUCCESS)
                    .data(result.getPlan())
                    .build();
        } else {
            return Result.withRetCode(FAIL_OP)
                    .message("未查询到课程计划")
                    .build();
        }
    }

    /**
     * 更新课程计划，这个函数会将新传入的课程计划暂存起来，直到真正审核完成的时候才会将计划更新
     * @param plan 新传入的计划内容
     */
    @PutMapping("/{course_id}/plan")
    public Result updateCoursePlan(
            @PathVariable("course_id") Integer courseId,
            @RequestParam(P_COURSE_PLAN) String plan
    ) {
        RetCode retCode = planService.stagePlan(courseId, plan);
        if (retCode == SUCCESS) {
            return Result.withRetCode(SUCCESS)
                    .build();
        } else {
            return Result.withRetCode(retCode)
                    .message("更新课程计划失败")
                    .build();
        }
    }

    /**
     * 课程提交审核
     * @return 是否提交审核成功
     */
    @PostMapping("/{course_id}")
    public Result commitCourse(
            @PathVariable("course_id") Integer courseId
    ) {
        boolean status = courseService.commitCourse(courseId);
        if (status) {
            return Result.withRetCode(SUCCESS)
                    .build();
        } else {
            return Result.withRetCode(FAIL_OP)
                    .message("课程提交审核失败")
                    .build();
        }
    }
}
