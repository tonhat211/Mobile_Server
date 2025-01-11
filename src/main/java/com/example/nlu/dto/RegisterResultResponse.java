package com.example.nlu.dto;

import java.util.List;

public class RegisterResultResponse {
    private List<RegisteringSubjectClassResponse> subjectClasses;
    private int status;

    public RegisterResultResponse(List<RegisteringSubjectClassResponse> subjectClasses, int status) {
        this.subjectClasses = subjectClasses;
        this.status = status;
    }

    public List<RegisteringSubjectClassResponse> getSubjectClasses() {
        return subjectClasses;
    }

    public void setSubjectClasses(List<RegisteringSubjectClassResponse> subjectClasses) {
        this.subjectClasses = subjectClasses;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}