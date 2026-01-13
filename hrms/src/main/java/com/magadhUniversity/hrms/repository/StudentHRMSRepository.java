package com.magadhUniversity.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magadhUniversity.hrms.model.StudentHRMS;

public interface StudentHRMSRepository extends JpaRepository<StudentHRMS, Long> {
}
