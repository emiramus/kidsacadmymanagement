package com.kidsacademy.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class StudentDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Past
    private LocalDate birthDate;
    @Email
    private String email;
    @Pattern(regexp = "\\+?\\d{10,15}")
    private String phoneNumber;
    @Min(3)
    @Max(18)
    private Integer age;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}
