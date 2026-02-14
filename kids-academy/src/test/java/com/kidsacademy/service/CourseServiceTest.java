package com.kidsacademy.service;

import com.kidsacademy.dto.CourseDTO;
import com.kidsacademy.entity.Course;
import com.kidsacademy.entity.Teacher;
import com.kidsacademy.entity.Classroom;
import com.kidsacademy.repository.CourseRepository;
import com.kidsacademy.repository.TeacherRepository;
import com.kidsacademy.repository.ClassroomRepository;
import com.kidsacademy.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private ClassroomRepository classroomRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCourse_Success() {

        CourseDTO dto = new CourseDTO();
        dto.setCourseName("Art");
        dto.setTeacherId(1);
        dto.setClassroomId(1);

        Teacher teacher = new Teacher();
        teacher.setId(1);

        Classroom classroom = new Classroom();
        classroom.setId(1);

        Course saved = new Course();
        saved.setId(1);
        saved.setCourseName("Art");

        when(teacherRepository.findById(1)).thenReturn(Optional.of(teacher));
        when(classroomRepository.findById(1)).thenReturn(Optional.of(classroom));
        when(courseRepository.save(any(Course.class))).thenReturn(saved);

        Course result = courseService.createCourse(dto);

        assertNotNull(result);
        assertEquals("Art", result.getCourseName());
        verify(courseRepository).save(any(Course.class));
    }

    @Test
    void testCreateCourse_TeacherNotFound() {

        CourseDTO dto = new CourseDTO();
        dto.setTeacherId(99);

        when(teacherRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.createCourse(dto);
        });
    }

    @Test
    void testGetAllCourses() {

        when(courseRepository.findAll()).thenReturn(List.of(new Course(), new Course()));

        List<Course> courses = courseService.getAllCourses();

        assertEquals(2, courses.size());
        verify(courseRepository).findAll();
    }

    @Test
    void testGetCourseById_Found() {

        Course course = new Course();
        course.setId(1);

        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        Course result = courseService.getCourseById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteCourse() {

        doNothing().when(courseRepository).deleteById(1);

        courseService.deleteCourse(1);

        verify(courseRepository).deleteById(1);
    }
}
