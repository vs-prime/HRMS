package com.magadhUniversity.hrms.service;

import com.magadhUniversity.hrms.model.AttendanceHRMS;

import java.util.List;

public interface AttendanceHRMSService {
    List<AttendanceHRMS> findAttendances();
    List<AttendanceHRMS> findAttendancesByUsername(String studentId);
    void markAttendance(AttendanceHRMS attendanceHRMS);
    AttendanceHRMS getAttendanceId(Long id);
    void deleteAttendance(Long id);
}
