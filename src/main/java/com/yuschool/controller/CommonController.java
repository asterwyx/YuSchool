package com.yuschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login.html";
    }
}
