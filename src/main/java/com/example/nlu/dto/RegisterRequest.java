package com.example.nlu.dto;

public class RegisterRequest {
    private long semesterID;
    private long userID;
    private long subjectClassID;
    private String queryFilter;
    private String searchInput;

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public String getQueryFilter() {
        return queryFilter;
    }

    public void setQueryFilter(String queryFilter) {
        this.queryFilter = queryFilter;
    }

    public long getSubjectClassID() {
        return subjectClassID;
    }

    public void setSubjectClassID(long subjectClassID) {
        this.subjectClassID = subjectClassID;
    }

    public long getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(long semesterID) {
        this.semesterID = semesterID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

}