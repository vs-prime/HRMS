package com.magadhUniversity.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.magadhUniversity.hrms.model.EmployeeHRMS;
import com.magadhUniversity.hrms.model.StudentHRMS;
import com.magadhUniversity.hrms.service.EmployeeHRMSService;
import com.magadhUniversity.hrms.service.StudentHRMSService;

@Controller
public class HRMSController {
    @Autowired
    private EmployeeHRMSService employeeHRMSService;

    @Autowired
    private StudentHRMSService studentHRMSService;

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employees", employeeHRMSService.findEmployees());
        return "employees";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentHRMSService.findStudents());
        return "students";
    }

    @GetMapping("/registerEmployee")
    public String registerEmployee(Model model) {
        EmployeeHRMS employeeHRMS = new EmployeeHRMS();
        model.addAttribute("employee", employeeHRMS);
        return "registerEmployee";
    }

    @GetMapping("/registerStudent")
    public String registerStudent(Model model) {
        StudentHRMS studentHRMS = new StudentHRMS();
        model.addAttribute("student", studentHRMS);
        return "registerStudent";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") EmployeeHRMS employeeHRMS) {
        employeeHRMSService.saveEmployee(employeeHRMS);
        return "redirect:/employees";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") StudentHRMS studentHRMS) {
        studentHRMSService.saveStudent(studentHRMS);
        return "redirect:/students";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(Model model, @PathVariable Long id) {
        EmployeeHRMS employeeHRMS = employeeHRMSService.getEmployeeId(id);
        model.addAttribute("employee", employeeHRMS);
        return "updateFormEmployee";
    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(Model model, @PathVariable Long id) {
        StudentHRMS studentHRMS = studentHRMSService.getStudentId(id);
        model.addAttribute("student", studentHRMS);
        return "updateFormStudent";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeHRMSService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentHRMSService.deleteStudent(id);
        return "redirect:/students";
    }
}
