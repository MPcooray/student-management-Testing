package com.example.students;

import jakarta.validation.constraints.Pattern;

public class StudentUpdateRequest {
    // Only grade is editable
    @Pattern(regexp = "A|B|C|D|E|F", message = "grade must be one of A,B,C,D,E,F")
    private String grade;

    public StudentUpdateRequest() {}
    public StudentUpdateRequest(String grade) { this.grade = grade; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
