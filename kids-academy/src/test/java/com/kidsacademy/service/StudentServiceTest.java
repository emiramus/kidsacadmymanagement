package com.kidsacademy.service;

import com.kidsacademy.dto.StudentDTO;
import com.kidsacademy.entity.Student;
import com.kidsacademy.repository.StudentRepository;
import com.kidsacademy.exception.BusinessLogicException;
import com.kidsacademy.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent_Success() {

        StudentDTO dto = new StudentDTO();
        dto.setFirstName("Valbona");
        dto.setLastName("Krasniqi");
        dto.setEmail("valbona@test.com");
        dto.setBirthDate(LocalDate.of(2010,1,1));
        dto.setAge(12);

        Student saved = new Student();
        saved.setId(1);
        saved.setFirstName("Valbona");

        when(studentRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(saved);

        Student result = studentService.createStudent(dto);

        assertNotNull(result);
        assertEquals("Valbona", result.getFirstName());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void testCreateStudent_EmailAlreadyExists() {

        StudentDTO dto = new StudentDTO();
        dto.setEmail("existing@test.com");

        when(studentRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(BusinessLogicException.class, () -> {
            studentService.createStudent(dto);
        });

        verify(studentRepository, never()).save(any());
    }

    @Test
    void testGetAllStudents() {

        when(studentRepository.findAll()).thenReturn(List.of(new Student(), new Student()));

        List<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
        verify(studentRepository).findAll();
    }

    @Test
    void testGetStudentById_Found() {

        Student student = new Student();
        student.setId(1);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetStudentById_NotFound() {

        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getStudentById(1);
        });
    }
}
