package com.example.nlu.repository;

import com.example.nlu.model.SemesterScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterScoreRepository extends JpaRepository<SemesterScore, Long> {
    @Query("SELECT s FROM SemesterScore s WHERE s.student.id = :userID AND s.semester.id = :semesterID")
    SemesterScore findBySemester(@Param("userID") Long userID,@Param("semesterID") Long semesterID);

    @Query("SELECT s FROM SemesterScore s WHERE s.student.id = :userID")
    List<SemesterScore> findAllByUser(@Param("userID") Long userID);

}


