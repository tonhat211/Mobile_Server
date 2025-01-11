package com.example.nlu.controller;

import com.example.nlu.dto.ExamScheduleRequest;
import com.example.nlu.dto.SubjectClassResponse;
import com.example.nlu.dto.SubjectRequest;
import com.example.nlu.model.ExamSchedule;
import com.example.nlu.model.Semester;
import com.example.nlu.repository.ExamScheduleRepository;
import com.example.nlu.repository.SemesterRepository;
import com.example.nlu.repository.SubjectClassRepository;
import com.example.nlu.security.JwtUtils;
import com.example.nlu.service.Constant;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/examschedule")
public class ExamScheduleController {

    @Autowired
    private ExamScheduleRepository examScheduleRepository;

    @PostMapping("/schedule")
    public List<ExamSchedule> getExamScheduleOfUserBySemesterID(@RequestBody ExamScheduleRequest params, @RequestHeader("Authorization") String token) {
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
        return examScheduleRepository.findOfUserBySemester(params.getUserID(),params.getSemesterID());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }
}
