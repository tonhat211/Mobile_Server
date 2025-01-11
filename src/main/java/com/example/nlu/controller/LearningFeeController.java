package com.example.nlu.controller;

import com.example.nlu.dto.LearningFeeAllResponse;
import com.example.nlu.dto.LearningFeeRequest;
import com.example.nlu.dto.LearningFeeSemesterResponse;
import com.example.nlu.model.ExamSchedule;
import com.example.nlu.model.LearningFee;
import com.example.nlu.model.Semester;
import com.example.nlu.repository.LearningFeeRepository;
import com.example.nlu.security.JwtUtils;
import com.example.nlu.service.Constant;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/learningfee")
public class LearningFeeController {

    @Autowired
    private LearningFeeRepository learningFeeRepository;

    @PostMapping("/semester")
    public List<LearningFeeSemesterResponse> getLearningOfUserBySemesterID(@RequestBody LearningFeeRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("learning fee of user by semester");
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
        List<LearningFee> learningFees = learningFeeRepository.findByUserAndSemester(params.getUserID(),params.getSemesterID(), Constant.CONFIRMED_STATUS);
        List<LearningFeeSemesterResponse> learningFeeSemesterResponses = new ArrayList<>();
        for (LearningFee learningFee : learningFees) {
            learningFeeSemesterResponses.add(new LearningFeeSemesterResponse(learningFee));
        }
        return learningFeeSemesterResponses;
    }

    @PostMapping("/semesters")
    public List<LearningFeeAllResponse> getALlLearningFeeOfUser(@RequestBody LearningFeeRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("all learning fee of user");
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
        List<LearningFee> learningFees = learningFeeRepository.findAllByUser(params.getUserID(), Constant.CONFIRMED_STATUS);
        System.out.println("all learning fees:" + learningFees.size());
        LinkedHashMap<Semester,List<LearningFee>> map= new LinkedHashMap<>();
        for(LearningFee learningFee : learningFees){
            if(!map.containsKey(learningFee.getSemester()))
                map.put(learningFee.getSemester(),new ArrayList<>());

            map.get(learningFee.getSemester()).add(learningFee);

        }
        List<LearningFeeAllResponse> learningFeeAllResponses = new ArrayList<>();
        for (Map.Entry<Semester, List<LearningFee>> entry : map.entrySet()) {
            learningFeeAllResponses.add(new LearningFeeAllResponse(entry.getKey(),entry.getValue()));
        }
        return learningFeeAllResponses;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }
}
