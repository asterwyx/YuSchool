package com.yuschool.controller;

import com.yuschool.service.CourseService;
import com.yuschool.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/courses")
@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public Result getAllCourses() {
        return Result.builder()
                .data(courseService.getAllCourses())
                .build();
    }
}
