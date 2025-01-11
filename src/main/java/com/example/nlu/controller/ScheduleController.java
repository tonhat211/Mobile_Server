package com.example.nlu.controller;

import com.example.nlu.dto.InitialProgramRequest;
import com.example.nlu.dto.SubjectClassResponse;
import com.example.nlu.dto.SubjectRequest;
import com.example.nlu.model.*;
import com.example.nlu.repository.*;
import com.example.nlu.security.JwtUtils;
import com.example.nlu.service.Constant;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/schedule")
public class ScheduleController {

    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectClassRepository subjectClassRepository;
    @Autowired
    private InitialProgramDetailRepository initialProgramDetailRepository;

    @GetMapping("/semesters")
    public List<Semester> getAllSemester() {
        System.out.println("Get semesters");
        return semesterRepository.findByAvaIAndSort(Constant.ACTIVE_STATUS, Sort.by(Sort.Order.desc("id")));

    }

    @PostMapping("/subjectclasses")
    public List<SubjectClassResponse> getActiveSubjectsByUserID(@RequestBody SubjectRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("active subject of user");
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token không tồn tại");
        }
        try {

            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                throw new UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            throw new UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
        return subjectClassRepository.findActiveSubjectClassesByUserID(params.getUserID(),params.getSemesterID(), Constant.DELETED_STATUS);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

    @PostMapping("/initialprograms")
    public List<InitialProgramDetail> getInitialProgramOfUsre(@RequestBody InitialProgramRequest params) {
        System.out.println("initialprogram of user: " + params.getUserID());
        long studentID = params.getUserID();
        Optional<Student> studentOptional = studentRepository.findById(studentID);
        if (!studentOptional.isPresent()) {
            return null;
        }

         Student student = studentOptional.get();
        String major = student.getMajor();
        int year = student.getYear();
        System.out.println("major: " + major);
        System.out.println("year: " + year);
        return initialProgramDetailRepository.findByMajorAndYear(major, year);
    }


}
