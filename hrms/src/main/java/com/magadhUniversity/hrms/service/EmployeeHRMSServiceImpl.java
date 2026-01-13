package com.magadhUniversity.hrms.service;

import com.magadhUniversity.hrms.model.EmployeeHRMS;
import com.magadhUniversity.hrms.repository.EmployeeHRMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeHRMSServiceImpl implements EmployeeHRMSService {

    @Autowired
    private EmployeeHRMSRepository employeeHRMSRepository;

    @Override
    public void saveEmployee(EmployeeHRMS employee) {
        employeeHRMSRepository.save(employee);
    }

    @Override
    public Iterable<EmployeeHRMS> findEmployees() {
        return employeeHRMSRepository.findAll();
    }

    @Override
    public EmployeeHRMS getEmployeeId(Long id) {
        return employeeHRMSRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeHRMSRepository.deleteById(id);
    }

    @Override
    @Scheduled(fixedDelay = 60000) // Run every 60 seconds
    public void updateEmployeeUsernames() {
        List<EmployeeHRMS> employees = employeeHRMSRepository.findAll();
        for (EmployeeHRMS employee : employees) {
            if (employee.getUsername() == null || employee.getUsername().isEmpty()) {
                employee.setDefaultUsername();
                employeeHRMSRepository.save(employee);
            }
        }
    }
}
