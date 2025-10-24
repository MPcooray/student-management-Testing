package com.example.students.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class StudentCreateRequest {
    @NotBlank(message = "name is required")
    private String name;

    @Pattern(regexp = "A|B|C|D|E|F", message = "grade must be one of A,B,C, D, E, F")
    private String grade;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
