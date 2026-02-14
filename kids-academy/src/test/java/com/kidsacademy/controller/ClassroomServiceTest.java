package com.kidsacademy.controller;

import com.kidsacademy.dto.ClassroomDTO;
import com.kidsacademy.entity.Classroom;
import com.kidsacademy.service.ClassroomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClassroomControllerTest {

    @Mock
    private ClassroomService classroomService;

    @InjectMocks
    private ClassroomController classroomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClassroom() {

        ClassroomDTO dto = new ClassroomDTO();
        dto.setName("Room B");
        dto.setCapacity(25);

        Classroom classroom = new Classroom();
        classroom.setId(1);
        classroom.setName("Room B");

        when(classroomService.createClassroom(dto)).thenReturn(classroom);

        ResponseEntity<Classroom> response =
                classroomController.createClassroom(dto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Room B", response.getBody().getName());

        verify(classroomService).createClassroom(dto);
    }

    @Test
    void testGetAllClassrooms() {

        when(classroomService.getAllClassrooms())
                .thenReturn(List.of(new Classroom(), new Classroom()));

        ResponseEntity<List<Classroom>> response =
                classroomController.getAllClassrooms();

        assertEquals(2, response.getBody().size());
        verify(classroomService).getAllClassrooms();
    }

    @Test
    void testDeleteClassroom() {

        doNothing().when(classroomService).deleteClassroom(1);

        ResponseEntity<Void> response =
                classroomController.deleteClassroom(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(classroomService).deleteClassroom(1);
    }
}
