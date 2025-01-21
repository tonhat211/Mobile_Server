package com.example.nlu.repository;

import com.example.nlu.model.Student;
import com.example.nlu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT t FROM Student t WHERE CAST(t.id AS string) LIKE %:idPart%")
    List<Student> findByIdLike(@Param("idPart") String idPart);
}


