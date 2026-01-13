package com.magadhUniversity.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.magadhUniversity.hrms.model.EmployeeHRMS;

@Repository
public interface EmployeeHRMSRepository extends JpaRepository<EmployeeHRMS, Long> {
    EmployeeHRMS findByUsername(String username); // Add this method
}
