package com.magadhUniversity.hrms.service;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.magadhUniversity.hrms.model.StudentHRMS;
import com.magadhUniversity.hrms.repository.StudentHRMSRepository;

import java.util.Date;

@Service
public class StudentHRMSServiceImpl implements StudentHRMSService {
    @Autowired
    private StudentHRMSRepository studentHRMSRepository;

    @Override
    public void saveStudent(StudentHRMS student) {
        studentHRMSRepository.save(student);
    }

    @Override
    public Iterable<StudentHRMS> findStudents() {
        return studentHRMSRepository.findAll();
    }

    @Override
    public StudentHRMS getStudentId(Long id) {
        StudentHRMS student = studentHRMSRepository.findById(id).orElseThrow(() -> new FetchNotFoundException("Student", id));
        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        studentHRMSRepository.deleteById(id);
    }

    public void addStudents() {
        int studentsPerDept = 5;
        String[] departments = {"CSE", "ISE", "ECE", "EEE", "MBA", "MCA"};

        for (String dept : departments) {
            for (int i = 1; i <= studentsPerDept; i++) {
                String section = (i <= 60) ? "A" : (i <= 120) ? "B" : "C";
                String usn = String.format("MU2024/%s/%03d", dept, i);
                StudentHRMS student = new StudentHRMS();
                student.setUsn(usn);
                student.setDepartment(dept);
                student.setSection(section);
                student.setAttendanceDate(new Date());
                student.setPresent(false);
                saveStudent(student);
            }
        }
    }
}
