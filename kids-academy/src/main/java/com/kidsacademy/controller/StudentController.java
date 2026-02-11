package com.kidsacademy.controller;

import com.kidsacademy.dto.StudentDTO;
import com.kidsacademy.entity.Student;
import com.kidsacademy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student create(@Valid @RequestBody StudentDTO dto) {
        return studentService.createStudent(dto);
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String lastName) {
        return studentService.searchByLastName(lastName);
    }
}
