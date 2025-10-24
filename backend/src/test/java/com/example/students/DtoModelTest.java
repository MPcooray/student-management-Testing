package com.example.students;

import com.example.students.controllers.StudentCreateRequest;
import com.example.students.controllers.StudentUpdateRequest;
import com.example.students.models.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Small DTO/model smoke tests to exercise constructors, getters and setters
 * so JaCoCo marks these simple methods as covered.
 */
class DtoModelTest {

    @Test
    void studentGettersSettersAndConstructors() {
        // test full-arg constructor and getters
        var s = new Student(1L, "Alice", "A");
        assertEquals(1L, s.getId());
        assertEquals("Alice", s.getName());
        assertEquals("A", s.getGrade());

        // test setters and default ctor
        var s2 = new Student();
        s2.setId(2L);
        s2.setName("Bob");
        s2.setGrade("B");
        assertEquals(2L, s2.getId());
        assertEquals("Bob", s2.getName());
        assertEquals("B", s2.getGrade());
    }

    @Test
    void createRequestGettersSetters() {
        var req = new StudentCreateRequest();
        req.setName("Charlie");
        req.setGrade("C");
        assertEquals("Charlie", req.getName());
        assertEquals("C", req.getGrade());

        var req2 = new StudentCreateRequest("Dana", "D");
        assertEquals("Dana", req2.getName());
        assertEquals("D", req2.getGrade());
    }

    @Test
    void updateRequestGettersSetters() {
        var u = new StudentUpdateRequest();
        u.setGrade("F");
        assertEquals("F", u.getGrade());
    }
}
