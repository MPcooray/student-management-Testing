package com.example.students;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    // UPDATED: optional ?grade=A filter
    @GetMapping("/students")
    public List<Student> list(@RequestParam(required = false) String grade) {
        if (grade == null || grade.isBlank()) return service.findAll();
        if (!grade.matches("A|B|C|D|E|F")) {
            throw new ResponseStatusException(BAD_REQUEST, "grade filter must be A–F");
        }
        return service.findByGrade(grade);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> create(@Valid @RequestBody StudentCreateRequest req) {
        Student saved = service.add(req.getName().trim(), req.getGrade());
        return ResponseEntity.created(URI.create("/students/" + saved.getId())).body(saved);
    }

    @GetMapping("/students/{id}")
    public Student getOne(@PathVariable long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "student not found"));
    }

    // NEW: update grade
    @PutMapping("/students/{id}")
    public Student update(@PathVariable long id, @Valid @RequestBody StudentUpdateRequest req) {
        return service.updateGrade(id, req.getGrade())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "student not found"));
    }

    // NEW: delete
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean removed = service.delete(id);
        if (!removed) throw new ResponseStatusException(NOT_FOUND, "student not found");
        return ResponseEntity.noContent().build();
    }
}
