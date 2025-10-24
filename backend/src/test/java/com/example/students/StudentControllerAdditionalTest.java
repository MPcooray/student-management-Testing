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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerAdditionalTest {

    @Autowired MockMvc mockMvc;
    @Autowired StudentService studentService;
    @Autowired ObjectMapper objectMapper;

    @BeforeEach
    void reset() { studentService.clear(); }

    @Test
    void listByGrade_andInvalidFilter() throws Exception {
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name","A1","grade","A"))))
            .andExpect(status().isCreated());
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name","B1","grade","B"))))
            .andExpect(status().isCreated());

        mockMvc.perform(get("/students?grade=A"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].grade").value("A"));

        // invalid grade filter should return 400
        mockMvc.perform(get("/students?grade=Z"))
               .andExpect(status().isBadRequest());
    }

    @Test
    void updateStudent_andNotFound() throws Exception {
        // create
        var body = objectMapper.writeValueAsString(Map.of("name","Upd","grade","C"));
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isCreated());

        // update existing
        var upd = objectMapper.writeValueAsString(Map.of("grade","B"));
        mockMvc.perform(put("/students/1").contentType(MediaType.APPLICATION_JSON).content(upd))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.grade").value("B"));

        // update non-existent
        mockMvc.perform(put("/students/999").contentType(MediaType.APPLICATION_JSON).content(upd))
               .andExpect(status().isNotFound());
    }

    @Test
    void deleteStudent_andNotFound() throws Exception {
        // create
        var body = objectMapper.writeValueAsString(Map.of("name","Del","grade","D"));
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isCreated());

        // delete existing
        mockMvc.perform(delete("/students/1"))
               .andExpect(status().isNoContent());

        // now missing
        mockMvc.perform(get("/students/1"))
               .andExpect(status().isNotFound());

        // delete non-existent
        mockMvc.perform(delete("/students/999"))
               .andExpect(status().isNotFound());
    }
}
