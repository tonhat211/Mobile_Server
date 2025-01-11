package com.example.nlu.repository;

import com.example.nlu.model.InitialProgram;
import com.example.nlu.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialProgramRepository extends JpaRepository<InitialProgram, Long> {

}


