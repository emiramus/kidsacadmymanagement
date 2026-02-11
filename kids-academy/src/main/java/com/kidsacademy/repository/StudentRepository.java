package com.kidsacademy.repository;

import com.kidsacademy.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByLastNameContainingIgnoreCase(String lastName);
    boolean existsByEmail(String email);
}
