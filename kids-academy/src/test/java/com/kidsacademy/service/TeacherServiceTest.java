package com.kidsacademy.service;

import com.kidsacademy.dto.TeacherDTO;
import com.kidsacademy.entity.Teacher;
import com.kidsacademy.repository.TeacherRepository;
import com.kidsacademy.exception.BusinessLogicException;
import com.kidsacademy.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTeacher_Success() {

        TeacherDTO dto = new TeacherDTO();
        dto.setFirstName("Era");
        dto.setLastName("Mustafa");
        dto.setEmail("era@test.com");

        Teacher saved = new Teacher();
        saved.setId(1);
        saved.setFirstName("Era");

        when(teacherRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(teacherRepository.save(any(Teacher.class))).thenReturn(saved);

        Teacher result = teacherService.createTeacher(dto);

        assertNotNull(result);
        assertEquals("Era", result.getFirstName());
        verify(teacherRepository).save(any(Teacher.class));
    }

    @Test
    void testCreateTeacher_EmailAlreadyExists() {

        TeacherDTO dto = new TeacherDTO();
        dto.setEmail("existing@test.com");

        when(teacherRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(BusinessLogicException.class, () -> {
            teacherService.createTeacher(dto);
        });

        verify(teacherRepository, never()).save(any());
    }

    @Test
    void testGetAllTeachers() {

        when(teacherRepository.findAll()).thenReturn(List.of(new Teacher(), new Teacher()));

        List<Teacher> teachers = teacherService.getAllTeachers();

        assertEquals(2, teachers.size());
        verify(teacherRepository).findAll();
    }

    @Test
    void testGetTeacherById_Found() {

        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(teacherRepository.findById(1)).thenReturn(Optional.of(teacher));

        Teacher result = teacherService.getTeacherById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetTeacherById_NotFound() {

        when(teacherRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            teacherService.getTeacherById(1);
        });
    }
}
