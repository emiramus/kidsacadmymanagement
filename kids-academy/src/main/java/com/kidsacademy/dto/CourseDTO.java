package com.kidsacademy.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CourseDTO {
    @NotBlank
    private String courseName;
    @NotBlank
    private String description;
    @Min(1)
    private Integer durationWeeks;
    @Min(3)
    private Integer minAge;
    @Min(3)
    private Integer maxAge;
    @DecimalMin("0.0")
    private BigDecimal fee;
    @NotNull
    private Integer teacherId;
    @NotNull
    private Integer classroomId;

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getDurationWeeks() { return durationWeeks; }
    public void setDurationWeeks(Integer durationWeeks) { this.durationWeeks = durationWeeks; }
    public Integer getMinAge() { return minAge; }
    public void setMinAge(Integer minAge) { this.minAge = minAge; }
    public Integer getMaxAge() { return maxAge; }
    public void setMaxAge(Integer maxAge) { this.maxAge = maxAge; }
    public BigDecimal getFee() { return fee; }
    public void setFee(BigDecimal fee) { this.fee = fee; }
    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }
    public Integer getClassroomId() { return classroomId; }
    public void setClassroomId(Integer classroomId) { this.classroomId = classroomId; }
}
