package com.example.students;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import com.example.students.models.Student;
import com.example.students.services.StudentService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService svc;

    @AfterEach
    void tearDown() { svc.clear(); }

    @Test
    void addAndFindAll() {
        var s1 = svc.add("Alice", "A");
        var s2 = svc.add("Bob", "B");

        List<Student> all = svc.findAll();
        assertEquals(2, all.size(), "should contain two students");

        assertTrue(all.stream().anyMatch(s -> s.getName().equals("Alice")));
        assertTrue(all.stream().anyMatch(s -> s.getName().equals("Bob")));

        assertNotNull(s1.getId());
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

        var byId = svc.findById(id);
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
        assertNotNull(s1.getId());
        svc.clear();
        var s2 = svc.add("Frank", "B");
        assertNotNull(s2.getId());
    }
}
