package com.kidsacademy.controller;

import com.kidsacademy.dto.TeacherDTO;
import com.kidsacademy.entity.Teacher;
import com.kidsacademy.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    public TeacherControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTeacher() {
        TeacherDTO dto = new TeacherDTO();
        dto.setFirstName("Era");
        dto.setLastName("Mustafa");
        dto.setEmail("era.mustafa@hotmail.com");

        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setFirstName("Era");

        when(teacherService.createTeacher(dto)).thenReturn(teacher);

        ResponseEntity<Teacher> response = teacherController.createTeacher(dto);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Era", response.getBody().getFirstName());
        verify(teacherService, times(1)).createTeacher(dto);
    }
}
