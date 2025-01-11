package com.example.nlu.repository;

import com.example.nlu.model.Subject;
import com.example.nlu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAll();
//    @Query("SELECT s FROM Subject s WHERE s.startDate >= (SELECT sem.startDate FROM Semester sem WHERE sem.id = :semesterID) " +
//            "AND s.endDate <= (SELECT sem.endDate FROM Semester sem WHERE sem.id = :semesterID)")
//    List<Subject> findSubjectsBySemesterID(@Param("semesterID") Long semesterID);
//    @Query("SELECT s FROM Subject s WHERE s.startDate > :currentDate")
//    List<Subject> getSubjectsBeforeStartDate(LocalDate currentDate);
}


