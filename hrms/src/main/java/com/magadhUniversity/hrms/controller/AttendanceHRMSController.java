package com.magadhUniversity.hrms.controller;

import com.magadhUniversity.hrms.model.AttendanceHRMS;
import com.magadhUniversity.hrms.service.AttendanceHRMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AttendanceHRMSController {

    @Autowired
    private AttendanceHRMSService attendanceHRMSService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/attendance")
    public String viewAttendances(Model model, Principal principal, Authentication authentication) {
        String currentUsername = principal.getName();
        List<AttendanceHRMS> attendances;
        if (authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN") || role.getAuthority().equals("ROLE_MANAGER"))) {
            attendances = attendanceHRMSService.findAttendances();
        } else {
            attendances = attendanceHRMSService.findAttendancesByUsername(currentUsername);
        }
        model.addAttribute("attendances", attendances);
        return "attendances";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/markAttendance")
    public String markAttendanceForm(Model model) {
        AttendanceHRMS attendanceHRMS = new AttendanceHRMS();
        model.addAttribute("attendance", attendanceHRMS);
        return "markAttendance";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/saveAttendance")
    public String saveAttendance(@ModelAttribute("attendance") AttendanceHRMS attendanceHRMS) {
        attendanceHRMSService.markAttendance(attendanceHRMS);
        return "redirect:/attendance";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/updateAttendance/{id}")
    public String updateAttendanceForm(Model model, @PathVariable Long id) {
        AttendanceHRMS attendanceHRMS = attendanceHRMSService.getAttendanceId(id);
        model.addAttribute("attendance", attendanceHRMS);
        return "updateFormAttendance";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/deleteAttendance/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceHRMSService.deleteAttendance(id);
        return "redirect:/attendance";
    }

    @GetMapping("/userAttendance")
    public String viewUserAttendance(Model model, Principal principal) {
        String currentUsername = principal.getName();
        List<AttendanceHRMS> attendances = attendanceHRMSService.findAttendancesByUsername(currentUsername);
        model.addAttribute("attendances", attendances);
        return "userAttendance";
    }
}
