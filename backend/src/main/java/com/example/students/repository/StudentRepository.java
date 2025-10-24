package com.example.students.repository;

import com.example.students.models.Student;
import java.util.List;
import java.util.Optional;

/**
 * Placeholder repository interface kept for compatibility. The project was converted to
 * an in-memory implementation and no longer relies on Spring Data JPA. This interface
 * intentionally does not extend JpaRepository to avoid the JPA dependency.
 */
public interface StudentRepository {
    List<Student> findByGrade(String grade);
    List<Student> findAll();
    Student save(Student s);
    Optional<Student> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    void deleteAll();
}
