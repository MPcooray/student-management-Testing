package com.example.students.services;

import com.example.students.models.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory StudentService implementation.
 * Replaces repository-backed service so the app can run without any database.
 */
@Service
public class StudentService {
    private final Map<Long, Student> store = new LinkedHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Student> findByGrade(String grade) {
        var list = new ArrayList<Student>();
        for (var s : store.values()) if (Objects.equals(s.getGrade(), grade)) list.add(s);
        return list;
    }

    public Student add(String name, String grade) {
        var id = idCounter.incrementAndGet();
        var s = new Student(id, name, grade);
        store.put(id, s);
        return s;
    }

    public Optional<Student> findById(long id) { return Optional.ofNullable(store.get(id)); }

    public Optional<Student> updateGrade(long id, String newGrade) {
        var s = store.get(id);
        if (s == null) return Optional.empty();
        s.setGrade(newGrade);
        return Optional.of(s);
    }

    public boolean delete(long id) { return store.remove(id) != null; }

    public void clear() { store.clear(); idCounter.set(0); }
}
