package com.magadhUniversity.hrms.controller;

import com.magadhUniversity.hrms.model.LeaveRequest;
import com.magadhUniversity.hrms.service.LeaveRequestService;
import com.magadhUniversity.hrms.model.EmployeeHRMS;
import com.magadhUniversity.hrms.repository.EmployeeHRMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private EmployeeHRMSRepository employeeHRMSRepository;

    @GetMapping("/leaveRequests")
    public String viewLeaveRequests(Model model) {
        model.addAttribute("leaveRequests", leaveRequestService.findAllLeaveRequests());
        return "leaveRequests";
    }

    @GetMapping("/submitLeaveRequest")
    public String submitLeaveRequestForm(Model model) {
        LeaveRequest leaveRequest = new LeaveRequest();
        model.addAttribute("leaveRequest", leaveRequest);
        return "submitLeaveRequest";
    }

    @PostMapping("/saveLeaveRequest")
    public String saveLeaveRequest(@ModelAttribute("leaveRequest") LeaveRequest leaveRequest, Principal principal) {
        String username = principal.getName();
        System.out.println("Username: " + username); // Log the username
        EmployeeHRMS employee = employeeHRMSRepository.findByUsername(username);
        if (employee != null) {
            leaveRequest.setEmployeeId(employee.getId());
        } else {
            System.out.println("No employee found for username: " + username);
        }
        leaveRequest.setStatus("Pending");
        leaveRequestService.saveLeaveRequest(leaveRequest);
        return "redirect:/leaveRequests";
    }

    @GetMapping("/approveLeaveRequest/{id}")
    public String approveLeaveRequest(@PathVariable Long id) {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(id).orElse(null);
        if (leaveRequest != null) {
            leaveRequest.setStatus("Approved");
            leaveRequestService.saveLeaveRequest(leaveRequest);
        }
        return "redirect:/leaveRequests";
    }

    @GetMapping("/rejectLeaveRequest/{id}")
    public String rejectLeaveRequest(@PathVariable Long id) {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(id).orElse(null);
        if (leaveRequest != null) {
            leaveRequest.setStatus("Rejected");
            leaveRequestService.saveLeaveRequest(leaveRequest);
        }
        return "redirect:/leaveRequests";
    }

    @GetMapping("/deleteLeaveRequest/{id}")
    public String deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return "redirect:/leaveRequests";
    }
}
