package com.kidsacademy.repository;

import com.kidsacademy.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByActiveTrue();
    List<Course> findByCourseNameContainingIgnoreCase(String keyword);
    List<Course> findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(Integer minAge, Integer maxAge);
}
