package com.example.nlu.repository;

import com.example.nlu.model.BlockRegister;
import com.example.nlu.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlockRegisterRepository extends JpaRepository<BlockRegister, Long> {
    @Query("SELECT br.student.id " +
            "FROM BlockRegister br")
    List<Long> findBlockedIDs();

    @Modifying
    @Transactional
    @Query("DELETE FROM BlockRegister br WHERE br.student.id = :id")
    int deleteByUserId(@Param("id") Long id);


}


