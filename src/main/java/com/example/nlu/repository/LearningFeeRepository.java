package com.example.nlu.repository;

import com.example.nlu.model.LearningFee;
import com.example.nlu.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningFeeRepository extends JpaRepository<LearningFee, Long> {
    @Query("SELECT lf FROM LearningFee lf WHERE lf.student.id =:userID AND lf.semester.id=:semesterID AND lf.avai =:confirmStatus")
    List<LearningFee> findByUserAndSemester(@Param("userID") Long userID,@Param("semesterID") Long semesterID,@Param("confirmStatus") int confirmStatus);

    @Query("SELECT lf FROM LearningFee lf WHERE lf.student.id =:userID AND lf.avai =:confirmStatus ORDER BY lf.semester.id DESC")
    List<LearningFee> findAllByUser(@Param("userID") Long userID,@Param("confirmStatus") int confirmStatus);
}


