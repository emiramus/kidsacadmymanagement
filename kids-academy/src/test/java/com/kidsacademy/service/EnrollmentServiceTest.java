package com.kidsacademy.service;

import com.kidsacademy.dto.EnrollmentDTO;
import com.kidsacademy.entity.Course;
import com.kidsacademy.entity.Enrollment;
import com.kidsacademy.entity.Student;
import com.kidsacademy.repository.CourseRepository;
import com.kidsacademy.repository.EnrollmentRepository;
import com.kidsacademy.repository.StudentRepository;
import com.kidsacademy.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEnrollment_Success() {

        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setStudentId(1);
        dto.setCourseId(1);
        dto.setStartDate(LocalDate.now());

        Student student = new Student();
        student.setId(1);

        Course course = new Course();
        course.setId(1);

        Enrollment saved = new Enrollment();
        saved.setId(1);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(saved);

        Enrollment result = enrollmentService.createEnrollment(dto);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(enrollmentRepository).save(any(Enrollment.class));
    }

    @Test
    void testCreateEnrollment_StudentNotFound() {

        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setStudentId(99);

        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                enrollmentService.createEnrollment(dto));
    }

    @Test
    void testDeleteEnrollment() {

        doNothing().when(enrollmentRepository).deleteById(1);

        enrollmentService.deleteEnrollment(1);

        verify(enrollmentRepository).deleteById(1);
    }
}
