package com.magadhUniversity.hrms.controller;

import com.magadhUniversity.hrms.model.AttendanceHRMS;
import com.magadhUniversity.hrms.model.LeaveRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @GetMapping("/employee")
    public String employeeDashboard() {
        return "employeeDashboard";
    }

    @GetMapping("/employee/attendance")
    public String viewEmployeeAttendance() {
        return "viewEmployeeAttendance";
    }

    @GetMapping("/employee/leaveRequest")
    public String requestLeave() {
        return "leaveRequests";
    }

    @GetMapping("/employee/submitLeaveRequest")
    public String submitLeaveRequest(Model model) {
        model.addAttribute("leaveRequest", new LeaveRequest());
        return "submitLeaveRequest";
    }

    @GetMapping("/employee/markAttendance")
    public String markAttendance(Model model) {
        model.addAttribute("attendanceHRMS", new AttendanceHRMS());
        return "markAttendance";
    }

    @PostMapping("/employee/saveAttendance")
    public String saveAttendance(@ModelAttribute AttendanceHRMS attendanceHRMS, Model model) {
        System.out.println("Attendance saved for student: " + attendanceHRMS.getStudentId());
        model.addAttribute("successMessage", "Attendance saved successfully for student: " + attendanceHRMS.getStudentId());
        return "markAttendance";
    }


}
