package com.example.nlu.repository;

import com.example.nlu.dto.SubjectClassResponse;
import com.example.nlu.model.Subject;
import com.example.nlu.model.SubjectClass;
import com.example.nlu.service.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubjectClassRepository extends JpaRepository<SubjectClass, Long> {
    List<SubjectClass> findAll();
    @Query("SELECT r.subjectClass FROM Registration r " +
            "WHERE r.user.id = :userID " +
            "AND r.subjectClass.startDate >= (SELECT sem.startDate FROM Semester sem WHERE sem.id = :semesterID) "+
            "AND r.subjectClass.endDate <= (SELECT sem.endDate FROM Semester sem WHERE sem.id = :semesterID) " +
            "AND r.avai !=:regisStatus")
    List<SubjectClassResponse> findActiveSubjectClassesByUserID(@Param("userID") Long userID, @Param("semesterID") Long semesterID, @Param("regisStatus") int regisStatus);

    @Modifying
    @Transactional
    @Query("UPDATE SubjectClass sc SET sc.remainingQty = sc.remainingQty-1 WHERE sc.id = :id AND sc.remainingQty > 1")
    int decreaseRemainQty(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE SubjectClass sc SET sc.remainingQty = sc.remainingQty+1 WHERE sc.id = :id")
    int increaseRemainQty(@Param("id") Long id);

    @Query("SELECT sc FROM SubjectClass sc " +
            "WHERE sc.id IN :ids")
    List<SubjectClass> findSubjectClassByIDs( @Param("ids") List<Long> ids);




}


