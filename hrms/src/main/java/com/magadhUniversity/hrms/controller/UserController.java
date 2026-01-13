package com.magadhUniversity.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/userDashboard")
    public String userDashboard() {
        return "userDashboard";
    }

}
