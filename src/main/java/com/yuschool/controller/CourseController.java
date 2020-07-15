package com.yuschool.controller;

import com.yuschool.mapper.CourseMapper;
import com.yuschool.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CourseController {

    @Autowired
    CourseMapper courseMapper;

    @ResponseBody
    @GetMapping("/courses")
    public Result getAllCourses() {
        return Result.builder()
                .data(courseMapper.selectAll())
                .build();
    }
}
