package com.kidsacademy.controller;

import com.kidsacademy.dto.CourseDTO;
import com.kidsacademy.entity.Course;
import com.kidsacademy.service.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    public CourseControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCourse() {

        CourseDTO dto = new CourseDTO();
        dto.setCourseName("Art Class");
        dto.setDescription("Creative drawing class");

        Course course = new Course();
        course.setId(1);
        course.setCourseName("Art Class");

        when(courseService.createCourse(dto)).thenReturn(course);

        Course result = courseController.create(dto);

        assertNotNull(result);
        assertEquals("Art Class", result.getCourseName());
        verify(courseService, times(1)).createCourse(dto);
    }

    @Test
    void testGetAllCourses() {

        Course course1 = new Course();
        course1.setId(1);
        course1.setCourseName("Music");

        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseName("Robotics");

        when(courseService.getAllCourses()).thenReturn(List.of(course1, course2));

        List<Course> result = courseController.getAll();

        assertEquals(2, result.size());
        verify(courseService, times(1)).getAllCourses();
    }
}
