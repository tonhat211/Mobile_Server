package com.example.nlu.repository;

import com.example.nlu.model.InitialProgram;
import com.example.nlu.model.InitialProgramDetail;
import com.example.nlu.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InitialProgramDetailRepository extends JpaRepository<InitialProgramDetail, Long> {
    @Query("SELECT ipd FROM InitialProgramDetail ipd WHERE ipd.initialProgramID IN " +
            " (SELECT ip.id FROM InitialProgram ip WHERE ip.major = :major AND ip.year = :year)" +
            " ORDER BY ipd.semesterID desc ")
    List<InitialProgramDetail> findByMajorAndYear(@Param("major") String major, @Param("year") int year);
}


