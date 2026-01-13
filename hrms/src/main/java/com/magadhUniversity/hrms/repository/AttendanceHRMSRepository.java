package com.magadhUniversity.hrms.repository;

import com.magadhUniversity.hrms.model.AttendanceHRMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceHRMSRepository extends JpaRepository<AttendanceHRMS, Long> {
    List<AttendanceHRMS> findByStudentId(String studentId);
}
