package com.example.students;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for classes that live in the root package: StudentsApplication and the
 * root-level StudentUpdateRequest.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RootDtoAndAppTest {

    @Test
    void contextLoads() {
        // simple context load to cover StudentsApplication main/config
        assertTrue(true);
    }

    @Test
    void rootStudentUpdateRequestCoverage() {
        var r = new com.example.students.StudentUpdateRequest();
        r.setGrade("A");
        assertEquals("A", r.getGrade());

        var r2 = new com.example.students.StudentUpdateRequest("B");
        assertEquals("B", r2.getGrade());
    }

    @Test
    void invokeMainMethod() {
        // call main to ensure StudentsApplication.main is executed for coverage
        // pass a property to disable starting a real web server so tests don't fail if port 8080 is in use
        StudentsApplication.main(new String[]{"--spring.main.web-application-type=none"});
    }
}
