package com.yuschool.controller;

import com.yuschool.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/resources")
@RestController
public class ResourceController {

    @PostMapping
    public Result uploadResource(HttpServletRequest request) {
        Result result = new Result();
        return result;
    }
}
