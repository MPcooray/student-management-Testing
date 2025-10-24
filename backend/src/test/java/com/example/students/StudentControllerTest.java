package com.example.students;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import com.example.students.services.StudentService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired StudentService studentService;
    @Autowired ObjectMapper objectMapper;

    @BeforeEach
    void reset() { studentService.clear(); }

    @Test
    void addAndListStudents() throws Exception {
        var body = objectMapper.writeValueAsString(Map.of("name","Alice","grade","A"));

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", greaterThan(0)))
            .andExpect(jsonPath("$.name").value("Alice"))
            .andExpect(jsonPath("$.grade").value("A"));

        mockMvc.perform(get("/students"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void getStudentById_andNotFound() throws Exception {
        var body = objectMapper.writeValueAsString(Map.of("name","Bob","grade","B"));
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isCreated());

        mockMvc.perform(get("/students/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Bob"));

        mockMvc.perform(get("/students/999"))
               .andExpect(status().isNotFound());
    }

    @Test
    void validationErrors() throws Exception {
        // Empty name -> 400
        var bad1 = objectMapper.writeValueAsString(Map.of("name","", "grade","A"));
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(bad1))
               .andExpect(status().isBadRequest());

        // Invalid grade -> 400
        var bad2 = objectMapper.writeValueAsString(Map.of("name","Cara", "grade","Z"));
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(bad2))
               .andExpect(status().isBadRequest());
    }
}
