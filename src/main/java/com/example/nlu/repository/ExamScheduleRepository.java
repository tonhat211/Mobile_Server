package com.example.nlu.repository;

import com.example.nlu.dto.SubjectClassResponse;
import com.example.nlu.model.ExamSchedule;
import com.example.nlu.model.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Long> {
    @Query("SELECT es FROM ExamSchedule es " +
            "WHERE es.id IN (" +
            "   SELECT ed.examSchedule.id FROM ExamDetail ed WHERE ed.student.id = :userID" +
            ") AND es.semesterID = :semesterID " +
            "ORDER BY es.date ASC")
    List<ExamSchedule> findOfUserBySemester(@Param("userID") Long userID, @Param("semesterID") Long semesterID);

}


