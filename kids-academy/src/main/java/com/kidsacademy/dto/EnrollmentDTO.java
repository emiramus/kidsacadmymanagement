package com.kidsacademy.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class EnrollmentDTO {
    @NotNull
    private Integer studentId;
    @NotNull
    private Integer courseId;
    private LocalDate startDate;

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
}
