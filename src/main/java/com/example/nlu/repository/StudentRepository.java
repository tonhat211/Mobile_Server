package com.example.nlu.repository;

import com.example.nlu.model.Student;
import com.example.nlu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}


