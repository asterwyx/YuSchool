package com.yuschool.controller;

import com.yuschool.bean.Course;
import com.yuschool.service.CourseService;
import com.yuschool.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static com.yuschool.constants.DefaultValue.PK_NULL;
import static com.yuschool.constants.ParamKey.*;
import static com.yuschool.constants.enums.RetCode.*;

@RequestMapping("/courses")
@RestController
public class CourseController {

    public static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
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
    public Result uploadCourse(@RequestParam(P_COURSE_NAME) String courseName, @RequestParam(P_COURSE_INTRODUCTION) String introduction, @RequestParam(P_COURSE_COVER_PATH) String coverFilePath) {
        Course course = new Course();
        course.setId(PK_NULL);
        course.setCourseName(courseName);
        course.setCoverFilePath(coverFilePath);
        course.setIntroduction(introduction);
        course.setStarNum(0);
        boolean status = courseService.addCourse(course);
        if (status) {
            return Result.withRetCode(SUCCESS)
                    .data(course.getId())
                    .build();
        } else {
            logger.error("上传课程失败");
            return Result.withRetCode(FAIL_OP)
                    .message("上传课程失败")
                    .build();
        }
    }
}
