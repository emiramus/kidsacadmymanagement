package com.kidsacademy.service;

import com.kidsacademy.dto.ClassroomDTO;
import com.kidsacademy.entity.Classroom;
import com.kidsacademy.exception.ResourceNotFoundException;
import com.kidsacademy.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public Classroom createClassroom(ClassroomDTO dto) {
        Classroom classroom = new Classroom();
        classroom.setName(dto.getName());
        classroom.setCapacity(dto.getCapacity());
        return classroomRepository.save(classroom);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Classroom getClassroomById(Integer id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found"));
    }

    public Classroom updateClassroom(Integer id, ClassroomDTO dto) {
        Classroom classroom = getClassroomById(id);
        classroom.setName(dto.getName());
        classroom.setCapacity(dto.getCapacity());
        return classroomRepository.save(classroom);
    }

    public void deleteClassroom(Integer id) {
        Classroom classroom = getClassroomById(id);
        classroomRepository.delete(classroom);
    }
}
