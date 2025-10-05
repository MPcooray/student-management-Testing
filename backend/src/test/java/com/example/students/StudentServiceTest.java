package com.example.students;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService svc;

    @BeforeEach
    void setUp() {
        svc = new StudentService();
        svc.clear();
    }

    @Test
    void addAndFindAll() {
        var s1 = svc.add("Alice", "A");
        var s2 = svc.add("Bob", "B");

        List<Student> all = svc.findAll();
        assertEquals(2, all.size(), "should contain two students");

        assertTrue(all.stream().anyMatch(s -> s.getName().equals("Alice")));
        assertTrue(all.stream().anyMatch(s -> s.getName().equals("Bob")));

        assertTrue(s1.getId() > 0);
        assertTrue(s2.getId() > s1.getId());
    }

    @Test
    void findByGrade() {
        svc.add("Alice", "A");
        svc.add("Bob", "B");
        svc.add("Cara", "A");

        List<Student> aStudents = svc.findByGrade("A");
        assertEquals(2, aStudents.size());
        assertEquals("Alice", aStudents.get(0).getName());
        assertEquals("Cara", aStudents.get(1).getName());
    }

    @Test
    void findByIdAndUpdateAndDelete() {
        var s = svc.add("Dave", "C");
        long id = s.getId();

        Optional<Student> byId = svc.findById(id);
        assertTrue(byId.isPresent());
        assertEquals("Dave", byId.get().getName());

        var updated = svc.updateGrade(id, "B");
        assertTrue(updated.isPresent());
        assertEquals("B", updated.get().getGrade());

        assertTrue(svc.delete(id));
        assertFalse(svc.findById(id).isPresent());

        // deleting non-existent returns false
        assertFalse(svc.delete(9999));
    }

    @Test
    void clearResetsSequence() {
        var s1 = svc.add("Eve", "A");
        assertEquals(1, s1.getId());
        svc.clear();
        var s2 = svc.add("Frank", "B");
        assertEquals(1, s2.getId(), "id sequence should reset after clear");
    }
}
