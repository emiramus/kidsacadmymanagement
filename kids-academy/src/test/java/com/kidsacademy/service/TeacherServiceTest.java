package com.kidsacademy.service;

import com.kidsacademy.dto.TeacherDTO;
import com.kidsacademy.entity.Teacher;
import com.kidsacademy.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    public TeacherServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTeacher() {
        TeacherDTO dto = new TeacherDTO();
        dto.setFirstName("Era");
        dto.setLastName("Mustafa");
        dto.setEmail("era.mustafa@hotmail.com");

        Teacher savedTeacher = new Teacher();
        savedTeacher.setId(1);
        savedTeacher.setFirstName("Era");

        when(teacherRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(teacherRepository.save(any(Teacher.class))).thenReturn(savedTeacher);

        Teacher result = teacherService.createTeacher(dto);
        assertNotNull(result);
        assertEquals("Era", result.getFirstName());
        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }
}
