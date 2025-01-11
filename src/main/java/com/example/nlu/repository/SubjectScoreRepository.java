package com.example.nlu.repository;

import com.example.nlu.model.SemesterScore;
import com.example.nlu.model.SubjectScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectScoreRepository extends JpaRepository<SubjectScore, Long> {
   @Query("SELECT s FROM SubjectScore s " +
           "JOIN FETCH s.subject sub " +
           "WHERE s.student.id = :userID AND s.semester.id = :semesterID")
   List<SubjectScore> findBySemester(@Param("userID") Long userID, @Param("semesterID") Long semesterID);

}


