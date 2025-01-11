package com.example.nlu.repository;

import com.example.nlu.dto.RegisteringSubjectClassResponse;
import com.example.nlu.dto.SubjectClassResponse;
import com.example.nlu.model.Registration;
import com.example.nlu.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Query("SELECT r FROM Registration r " +
            "WHERE r.user.id =:userID and  r.avai = :status")
    List<Registration> findRegisteredSubjectClass(@Param("userID") Long userID, @Param("status") int status);

    @Query("SELECT new com.example.nlu.dto.RegisteringSubjectClassResponse(r, sc) " +
            "FROM SubjectClass sc " +
            "LEFT JOIN Registration r ON sc.id = r.subjectClass.id AND r.avai !=:regisStatus " +
            "WHERE sc.classID IN (" +
            "   SELECT s.classID FROM Student s WHERE s.id = :userID" +
            ") AND sc.status=:subStatus")
    List<RegisteringSubjectClassResponse> findRegisterSubjectClassesByClassID(@Param("userID") Long userID,
                                                                              @Param("subStatus") int subStatus,
                                                                              @Param("regisStatus") int regisStatus);

    @Modifying
    @Transactional
    @Query("UPDATE Registration r SET r.avai = :deletedStatus WHERE r.user.id = :userId AND r.subjectClass.subject.id = :subjectId AND r.avai = :waitingStatus")
    int disableOldRegister(@Param("userId") Long userId, @Param("subjectId") Long subjectId, @Param("deletedStatus") int deletedStatus,@Param("waitingStatus") int waitingStatus);

    @Modifying
    @Transactional
    @Query("UPDATE Registration r SET r.avai = :deletedStatus WHERE r.user.id = :userId AND r.subjectClass.id = :subjectClassId AND r.avai = :waitingStatus")
    int cancelRegister(@Param("userId") Long userId, @Param("subjectClassId") Long subjectClassId, @Param("deletedStatus") int deletedStatus,@Param("waitingStatus") int waitingStatus);

    @Query("SELECT new com.example.nlu.dto.RegisteringSubjectClassResponse(r, sc) " +
            "FROM SubjectClass sc " +
            "LEFT JOIN Registration r ON sc.id = r.subjectClass.id AND r.avai != :regisStatus " +
            "WHERE sc.subject.id IN :subjectIds " +
            "AND sc.status = :subStatus")
    List<RegisteringSubjectClassResponse> findRegisterSubjectClassesByProgram(
            @Param("subjectIds") List<Long> subjectIds,
            @Param("subStatus") int subStatus,
            @Param("regisStatus") int regisStatus);

    @Query("SELECT r.subjectClass.id FROM Registration r " +
            "WHERE r.user.id =:userId and  r.avai = :waitingStatus")
    List<Long> findWaitingSubjectClassByUserID(@Param("userId") Long userId,@Param("waitingStatus") int waitingStatus);

    @Query("SELECT new com.example.nlu.dto.RegisteringSubjectClassResponse(r, sc) " +
            "FROM SubjectClass sc " +
            "LEFT JOIN Registration r ON sc.id = r.subjectClass.id AND r.avai != :regisStatus " +
            "WHERE (CAST(sc.subject.id AS string) LIKE %:searchInput% OR sc.subject.name LIKE %:searchInput%) " +
            "AND sc.status = :subStatus")
    List<RegisteringSubjectClassResponse> findRegisterSubjectClassesBySearchInput(@Param("searchInput") String searchInput, @Param("subStatus") int subStatus,@Param("regisStatus") int regisStatus);


}


