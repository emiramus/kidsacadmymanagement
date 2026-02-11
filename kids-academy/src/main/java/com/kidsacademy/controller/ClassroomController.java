package com.kidsacademy.controller;

import com.kidsacademy.dto.ClassroomDTO;
import com.kidsacademy.entity.Classroom;
import com.kidsacademy.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<Classroom> create(@Valid @RequestBody ClassroomDTO dto) {
        return new ResponseEntity<>(classroomService.createClassroom(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAll() {
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> get(@PathVariable Integer id) {
        return ResponseEntity.ok(classroomService.getClassroomById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classroom> update(@PathVariable Integer id, @Valid @RequestBody ClassroomDTO dto) {
        return ResponseEntity.ok(classroomService.updateClassroom(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok("Classroom deleted successfully");
    }
}
