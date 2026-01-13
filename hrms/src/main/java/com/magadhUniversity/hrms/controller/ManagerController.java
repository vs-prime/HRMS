package com.magadhUniversity.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    @GetMapping("/manager")
    public String managerDashboard() {
        // Code for manager dashboard
        return "managerDashboard";
    }

    @GetMapping("/manager/attendance")
    public String manageAttendance() {
        // Code for managing attendance
        return "manageAttendance";
    }

    @GetMapping("/manager/leaveRequests")
    public String viewLeaveRequests() {
        // Code for viewing leave requests
        return "leaveRequests";
    }
}
