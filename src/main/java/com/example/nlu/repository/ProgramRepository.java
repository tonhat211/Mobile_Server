package com.example.nlu.repository;

import com.example.nlu.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    @Query("SELECT p.subjectIDs FROM Program p WHERE p.major = (SELECT s.major FROM Student s WHERE s.id = :userID)")
    String findSubjectIdsByMajor(@Param("userID") Long userID);
}


