package com.example.nlu.repository;

import com.example.nlu.model.Semester;
import com.example.nlu.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    @Query("SELECT s FROM Semester s WHERE s.avai = :avai ORDER BY s.id DESC")
    List<Semester> findByAvaIAndSort(@Param("avai") int avai, Sort sort);}


