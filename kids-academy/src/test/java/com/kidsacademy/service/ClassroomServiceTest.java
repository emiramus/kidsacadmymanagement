package com.kidsacademy.service;

import com.kidsacademy.dto.ClassroomDTO;
import com.kidsacademy.entity.Classroom;
import com.kidsacademy.repository.ClassroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClassroomServiceTest {

    @Mock
    private ClassroomRepository classroomRepository;

    @InjectMocks
    private ClassroomService classroomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClassroom() {

        ClassroomDTO dto = new ClassroomDTO();
        dto.setName("Room A");
        dto.setCapacity(20);

        Classroom saved = new Classroom();
        saved.setId(1);
        saved.setName("Room A");

        when(classroomRepository.save(any(Classroom.class))).thenReturn(saved);

        Classroom result = classroomService.createClassroom(dto);

        assertNotNull(result);
        assertEquals("Room A", result.getName());
        verify(classroomRepository).save(any(Classroom.class));
    }

    @Test
    void testGetAllClassrooms() {

        when(classroomRepository.findAll())
                .thenReturn(List.of(new Classroom(), new Classroom()));

        List<Classroom> list = classroomService.getAllClassrooms();

        assertEquals(2, list.size());
        verify(classroomRepository).findAll();
    }

    @Test
    void testDeleteClassroom() {

        doNothing().when(classroomRepository).deleteById(1);

        classroomService.deleteClassroom(1);

        verify(classroomRepository).deleteById(1);
    }
}
