package com.magadhUniversity.hrms.service;

import com.magadhUniversity.hrms.model.AttendanceHRMS;
import com.magadhUniversity.hrms.repository.AttendanceHRMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceHRMSServiceImpl implements AttendanceHRMSService {

    @Autowired
    private AttendanceHRMSRepository attendanceHRMSRepository;

    @Override
    public List<AttendanceHRMS> findAttendances() {
        return attendanceHRMSRepository.findAll();
    }

    @Override
    public List<AttendanceHRMS> findAttendancesByUsername(String studentId) {
        return attendanceHRMSRepository.findByStudentId(studentId);
    }

    @Override
    public void markAttendance(AttendanceHRMS attendanceHRMS) {
        attendanceHRMSRepository.save(attendanceHRMS);
    }

    @Override
    public AttendanceHRMS getAttendanceId(Long id) {
        return attendanceHRMSRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceHRMSRepository.deleteById(id);
    }
}
