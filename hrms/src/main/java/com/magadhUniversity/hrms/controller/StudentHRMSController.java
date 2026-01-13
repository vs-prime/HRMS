package com.magadhUniversity.hrms.controller;

import com.magadhUniversity.hrms.model.StudentHRMS;
import com.magadhUniversity.hrms.service.StudentHRMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentHRMSController {

    @Autowired
    private StudentHRMSService studentService;

    @GetMapping("/students/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new StudentHRMS());
        return "registerStudent"; // Name of the HTML file
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute StudentHRMS student) {
        studentService.saveStudent(student);
        return "redirect:/students/view"; // After saving, redirect to view students
    }

    @GetMapping("/students/view")
    public String viewStudents(Model model) {
        model.addAttribute("students", studentService.findStudents());
        return "students"; // Name of the HTML file to display students
    }
}
