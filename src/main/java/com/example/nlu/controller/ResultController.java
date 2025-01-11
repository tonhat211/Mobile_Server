package com.example.nlu.controller;

import com.example.nlu.dto.LearningResultResponse;
import com.example.nlu.dto.ResultRequest;
import com.example.nlu.dto.SubjectClassRequest;
import com.example.nlu.dto.SubjectClassResponse;
import com.example.nlu.model.SemesterScore;
import com.example.nlu.model.SubjectClass;
import com.example.nlu.model.SubjectScore;
import com.example.nlu.model.TotalScore;
import com.example.nlu.repository.SemesterScoreRepository;
import com.example.nlu.repository.SubjectClassRepository;
import com.example.nlu.repository.SubjectScoreRepository;
import com.example.nlu.security.JwtUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/result")
public class ResultController {
    @Autowired
    private SubjectScoreRepository subjectScoreRepository;

    @Autowired
    private SemesterScoreRepository semesterScoreRepository;

    @PostMapping("/semester")
    public LearningResultResponse findByID(@RequestBody ResultRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("get score of: " + params.getUserID());
        if (token == null || token.isEmpty()) {
            throw new ResultController.UnauthorizedException("Token không tồn tại");
        }
        try {
            if (!JwtUtils.validateToken(token)) {
                throw new ResultController.UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            throw new ResultController.UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
        LearningResultResponse re = new LearningResultResponse(new ArrayList<>(),null,null);
        List<SubjectScore> subjectScores = subjectScoreRepository.findBySemester(params.getUserID(), params.getSemesterID());
        SemesterScore semesterScore = semesterScoreRepository.findBySemester(params.getUserID(), params.getSemesterID());
        re.setSubjectScores(subjectScores);
        re.setSemesterScore(semesterScore);

        List<SemesterScore> semesterScores = semesterScoreRepository.findAllByUser(params.getUserID());
        double avg10 =0;
        double avg4 =0;
        int totalCredit = 0;
        for (SemesterScore score : semesterScores) {
            avg10+=score.getAvgScore10();
            avg4+=score.getAvgScore4();
            totalCredit+=score.getReachedCredit();
        }
        avg10/=semesterScores.size();
        avg4/=semesterScores.size();
        TotalScore totalScore = new TotalScore(avg10,avg4,totalCredit);
        re.setTotalScore(totalScore);
        return re;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }


}
