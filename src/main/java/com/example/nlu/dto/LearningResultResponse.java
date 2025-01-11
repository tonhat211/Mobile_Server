package com.example.nlu.dto;

import com.example.nlu.model.SemesterScore;
import com.example.nlu.model.SubjectScore;
import com.example.nlu.model.TotalScore;

import java.util.List;

public class LearningResultResponse {
    private List<SubjectScore> subjectScores;
    private SemesterScore semesterScore;
    private TotalScore totalScore;

    public LearningResultResponse(List<SubjectScore> subjectScores, SemesterScore semesterScore, TotalScore totalScore) {
        this.subjectScores = subjectScores;
        this.semesterScore = semesterScore;
        this.totalScore = totalScore;
    }

    public List<SubjectScore> getSubjectScores() {
        return subjectScores;
    }

    public void setSubjectScores(List<SubjectScore> subjectScores) {
        this.subjectScores = subjectScores;
    }

    public SemesterScore getSemesterScore() {
        return semesterScore;
    }

    public void setSemesterScore(SemesterScore semesterScore) {
        this.semesterScore = semesterScore;
    }

    public TotalScore getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(TotalScore totalScore) {
        this.totalScore = totalScore;
    }
}