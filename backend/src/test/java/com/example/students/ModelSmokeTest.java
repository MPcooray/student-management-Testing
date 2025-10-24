package com.example.students;

import com.example.students.models.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModelSmokeTest {
  @Test void studentGettersSetters() {
    var s = new Student(1L, "Alice", "A");
    assertEquals("Alice", s.getName());
    assertEquals("A", s.getGrade());
    s.setName("Bob");
    s.setGrade("B");
    assertEquals("Bob", s.getName());
    assertEquals("B", s.getGrade());
  }
}
