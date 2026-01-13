package com.magadhUniversity.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminDashboard() {
        // Code for admin dashboard
        return "adminDashboard";
    }

    @GetMapping("/admin/users")
    public String manageUsers() {
        // Code for managing users
        return "manageUsers";
    }

    @GetMapping("/admin/departments")
    public String manageDepartments() {
        // Code for managing departments
        return "manageDepartments";
    }
}
