package com.kidsacademy.controller;

import com.kidsacademy.dto.CourseDTO;
import com.kidsacademy.entity.Course;
import com.kidsacademy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public Course create(@Valid @RequestBody CourseDTO dto) {
        return courseService.createCourse(dto);
    }

    @GetMapping
    public List<Course> getAll() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }
}
