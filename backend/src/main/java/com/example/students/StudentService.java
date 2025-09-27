package com.example.students;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// ...imports unchanged...

@Service
public class StudentService {
    private final Map<Long, Student> store = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    public List<Student> findAll() { return new ArrayList<>(store.values()); }

    // NEW: find by grade
    public List<Student> findByGrade(String grade) {
        return store.values().stream()
                .filter(s -> s.getGrade().equals(grade))
                .sorted(Comparator.comparingLong(Student::getId))
                .toList();
    }

    public Student add(String name, String grade) {
        long id = idSeq.getAndIncrement();
        Student s = new Student(id, name, grade);
        store.put(id, s);
        return s;
    }

    public Optional<Student> findById(long id) { return Optional.ofNullable(store.get(id)); }

    // NEW: update grade
    public Optional<Student> updateGrade(long id, String newGrade) {
        Student s = store.get(id);
        if (s == null) return Optional.empty();
        s.setGrade(newGrade);
        return Optional.of(s);
    }

    // NEW: delete
    public boolean delete(long id) {
        return store.remove(id) != null;
    }

    // for tests
    public void clear() {
        store.clear();
        idSeq.set(1);
    }
}
