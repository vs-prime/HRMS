package com.magadhUniversity.hrms.service;

import com.magadhUniversity.hrms.model.EmployeeHRMS;

public interface EmployeeHRMSService {
    void saveEmployee(EmployeeHRMS employee);
    Iterable<EmployeeHRMS> findEmployees();
    EmployeeHRMS getEmployeeId(Long id);
    void deleteEmployee(Long id);
    void updateEmployeeUsernames(); // Add automated username updates
}
