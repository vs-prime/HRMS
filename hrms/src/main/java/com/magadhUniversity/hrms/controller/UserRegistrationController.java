package com.magadhUniversity.hrms.controller;

import com.magadhUniversity.hrms.model.EmployeeHRMS;
import com.magadhUniversity.hrms.model.UserRegistration;
import com.magadhUniversity.hrms.service.EmployeeHRMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserRegistrationController {

    @Autowired
    private EmployeeHRMSService employeeHRMSService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistration", new UserRegistration());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userRegistration") UserRegistration userRegistration) {
        EmployeeHRMS employee = new EmployeeHRMS();
        employee.setFirstname(userRegistration.getFirstname());
        employee.setLastname(userRegistration.getLastname());
        employee.setEmail(userRegistration.getEmail());
        employee.setUsername(userRegistration.getUsername());
        employeeHRMSService.saveEmployee(employee);

        jdbcTemplate.update("INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)",
                userRegistration.getUsername(),
                passwordEncoder.encode(userRegistration.getPassword()),
                true);

        jdbcTemplate.update("INSERT INTO authorities (username, authority) VALUES (?, ?)",
                userRegistration.getUsername(),
                "ROLE_USER");

        return "redirect:/login";
    }
}
