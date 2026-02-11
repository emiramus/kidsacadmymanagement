package com.kidsacademy.service;

import com.kidsacademy.dto.StudentDTO;
import com.kidsacademy.entity.Student;
import com.kidsacademy.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    public StudentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        StudentDTO dto = new StudentDTO();
        dto.setFirstName("Valbona");
        dto.setLastName("Krasniqi");
        dto.setEmail("valbona.krasniqi@hotmail.com");
        dto.setBirthDate(LocalDate.of(2010, 1, 1));
        dto.setAge(12);

        Student savedStudent = new Student();
        savedStudent.setId(1);
        savedStudent.setFirstName("Valbona");
        savedStudent.setLastName("Krasniqi");

        when(studentRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        Student result = studentService.createStudent(dto);
        assertNotNull(result);
        assertEquals("Valbona", result.getFirstName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }
}
