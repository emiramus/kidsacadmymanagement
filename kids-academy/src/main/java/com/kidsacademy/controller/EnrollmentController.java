package com.kidsacademy.controller;

import com.kidsacademy.dto.EnrollmentDTO;
import com.kidsacademy.entity.Enrollment;
import com.kidsacademy.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public Enrollment enroll(@Valid @RequestBody EnrollmentDTO dto) {
        return enrollmentService.enrollStudent(dto);
    }

    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.getAllEnrollments();
    }
}
