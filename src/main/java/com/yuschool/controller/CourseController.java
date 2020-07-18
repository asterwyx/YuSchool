package com.yuschool.controller;

import com.yuschool.bean.Course;
import com.yuschool.service.CourseService;
import com.yuschool.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.yuschool.constants.DefaultValue.PK_NULL;
import static com.yuschool.constants.ParamKey.*;
import static com.yuschool.constants.enums.RetCode.*;

@RequestMapping("/courses")
@RestController
public class CourseController {

    public static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    CourseService courseService;

    @GetMapping
    public Result getAllCourses() {
        return Result.builder()
                .data(courseService.getAllCourses())
                .build();
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
        return Result.builder()
                .retCode(status ? SUCCESS : FAIL_OP)
                .data(course.getId())
                .build();
    }
}
