package com.kidsacademy.service;

import com.kidsacademy.dto.StudentDTO;
import com.kidsacademy.entity.Student;
import com.kidsacademy.exception.ResourceNotFoundException;
import com.kidsacademy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(StudentDTO dto) {
        if(studentRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Student s = new Student();
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setBirthDate(dto.getBirthDate());
        s.setEmail(dto.getEmail());
        s.setPhoneNumber(dto.getPhoneNumber());
        s.setAge(dto.getAge());
        return studentRepository.save(s);
    }

    public List<Student> getAllStudents() { return studentRepository.findAll(); }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public List<Student> searchByLastName(String lastName) {
        return studentRepository.findByLastNameContainingIgnoreCase(lastName);
    }
}
