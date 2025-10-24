package com.example.students.controllers;

import jakarta.validation.constraints.Pattern;

public class StudentUpdateRequest {
    @Pattern(regexp = "A|B|C|D|E|F", message = "grade must be one of A,B,C, D, E, F")
    private String grade;

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
