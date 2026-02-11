package com.kidsacademy.service;

import com.kidsacademy.dto.CourseDTO;
import com.kidsacademy.entity.Course;
import com.kidsacademy.entity.Teacher;
import com.kidsacademy.entity.Classroom;
import com.kidsacademy.exception.ResourceNotFoundException;
import com.kidsacademy.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassroomService classroomService;

    public Course createCourse(CourseDTO dto) {
        Course c = new Course();
        c.setCourseName(dto.getCourseName());
        c.setDescription(dto.getDescription());
        c.setDurationWeeks(dto.getDurationWeeks());
        c.setMinAge(dto.getMinAge());
        c.setMaxAge(dto.getMaxAge());
        c.setFee(dto.getFee());
        c.setActive(true);

        Teacher t = teacherService.getTeacherById(dto.getTeacherId());
        Classroom cr = classroomService.getClassroomById(dto.getClassroomId());
        c.setTeacher(t);
        c.setClassroom(cr);

        return courseRepository.save(c);
    }

    public List<Course> getAllCourses() { return courseRepository.findAll(); }

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }
}
