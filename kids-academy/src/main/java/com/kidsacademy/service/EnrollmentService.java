package com.kidsacademy.service;

import com.kidsacademy.dto.EnrollmentDTO;
import com.kidsacademy.entity.Enrollment;
import com.kidsacademy.entity.Student;
import com.kidsacademy.entity.Course;
import com.kidsacademy.exception.BusinessLogicException;
import com.kidsacademy.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    public Enrollment enrollStudent(EnrollmentDTO dto) {
        Student s = studentService.getStudentById(dto.getStudentId());
        Course c = courseService.getCourseById(dto.getCourseId());

        if(enrollmentRepository.existsByStudentIdAndCourseId(s.getId(), c.getId()))
            throw new BusinessLogicException("Student already enrolled in this course");

        Enrollment e = new Enrollment();
        e.setStudent(s);
        e.setCourse(c);
        e.setEnrollmentDate(LocalDate.now());
        e.setStartDate(dto.getStartDate() != null ? dto.getStartDate() : LocalDate.now().plusDays(7));
        e.setStatus("ACTIVE");

        return enrollmentRepository.save(e);
    }

    public List<Enrollment> getAllEnrollments() { return enrollmentRepository.findAll(); }
}
