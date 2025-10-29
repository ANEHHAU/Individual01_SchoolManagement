package com.example.individual01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController {

    @GetMapping("/user/info")
    public String userInfo() {
        return "auth/user";
    }

    @GetMapping("/admin/info")
    public String adminInfo() {
        return "auth/admin";
    }
}
