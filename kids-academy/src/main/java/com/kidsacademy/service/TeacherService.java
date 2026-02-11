package com.kidsacademy.service;

import com.kidsacademy.dto.TeacherDTO;
import com.kidsacademy.entity.Teacher;
import com.kidsacademy.exception.ResourceNotFoundException;
import com.kidsacademy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher createTeacher(TeacherDTO dto) {
        if(teacherRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email already registered: " + dto.getEmail());

        Teacher t = new Teacher();
        t.setFirstName(dto.getFirstName());
        t.setLastName(dto.getLastName());
        t.setEmail(dto.getEmail());
        t.setPhoneNumber(dto.getPhoneNumber());
        return teacherRepository.save(t);
    }

    public List<Teacher> getAllTeachers() { return teacherRepository.findAll(); }

    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }

    public Teacher updateTeacher(Integer id, TeacherDTO dto) {
        Teacher t = getTeacherById(id);
        t.setFirstName(dto.getFirstName());
        t.setLastName(dto.getLastName());
        t.setEmail(dto.getEmail());
        t.setPhoneNumber(dto.getPhoneNumber());
        return teacherRepository.save(t);
    }

    public void deleteTeacher(Integer id) {
        Teacher t = getTeacherById(id);
        teacherRepository.delete(t);
    }
}
