package com.example.nlu.controller;

import com.example.nlu.dto.RegisterRequest;
import com.example.nlu.dto.RegisteringSubjectClassResponse;
import com.example.nlu.dto.SubjectRequest;
import com.example.nlu.model.Semester;
import com.example.nlu.model.Subject;
import com.example.nlu.repository.SemesterRepository;
import com.example.nlu.repository.SubjectRepository;
import com.example.nlu.security.JwtUtils;
import com.example.nlu.service.Constant;
import com.example.nlu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/subject")
public class SubjectController {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/subjects")
    public List<Subject> findAll() {
        return subjectRepository.findAll();  // Gọi tới phương thức trong Service để lấy dữ liệu
    }

    @PostMapping("/subjectclasses")
    public List<Subject> getSubjects(@RequestBody SubjectRequest params, @RequestHeader("Authorization") String token) {
        // Kiểm tra token nếu cần thiết
        System.out.println("Subject: ");
        System.out.println("Server: token: " + token);
        if (token == null || token.isEmpty() || !JwtUtils.validateToken(token)) {
            throw new IllegalArgumentException("Token hết hạn");
        }

        return null;
    }

//    @PostMapping("/subjectsBySemester")
//    public List<Subject> getSubjectsBySemester(@RequestBody SubjectRequest params) {
//        // Lấy học kỳ từ database
//        Long semesterID = params.getSemesterID();
//        return subjectRepository.findSubjectsBySemesterID(semesterID);
//    }
//
//    @PostMapping("/registerSubject")
//    public List<Subject> getAbleRegisterSubject(@RequestBody SubjectRequest params) {
//        LocalDate today = LocalDate.now();
//        return subjectRepository.getSubjectsBeforeStartDate(today);
//
//    }

    @PostMapping("/search")
    public ResponseEntity<Subject> searchRegisterSubjectClassByClassID(@RequestBody SubjectRequest params) {
        System.out.println("search subject: " +params.getSubjectID());
        List<Subject> subjects = subjectRepository.findSubjectByID(params.getSubjectID());
        if (subjects.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(subjects.get(0));
    }


}
