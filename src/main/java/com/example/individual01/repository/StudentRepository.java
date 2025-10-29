package com.example.individual01.repository;

import com.example.individual01.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // List<Student> findByStudentNameContaining(String keyword);
}
