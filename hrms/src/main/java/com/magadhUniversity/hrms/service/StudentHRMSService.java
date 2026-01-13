package com.magadhUniversity.hrms.service;

import com.magadhUniversity.hrms.model.StudentHRMS;

public interface StudentHRMSService {
    void saveStudent(StudentHRMS student);
    Iterable<StudentHRMS> findStudents();
    StudentHRMS getStudentId(Long id);
    void deleteStudent(Long id);
    void addStudents(); // Add this line
}
