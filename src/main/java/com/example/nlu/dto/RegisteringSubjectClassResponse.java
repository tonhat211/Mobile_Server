package com.example.nlu.dto;

import com.example.nlu.model.Registration;
import com.example.nlu.model.SubjectClass;
import com.example.nlu.service.Constant;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisteringSubjectClassResponse {
    private long id;
    private long userID;
    private long subjectClassID;
    private String team;
    private String classID;
    private long subjectID;
    private String subjectName;
    private int subjectCredit;
    private String subjectGroup;
    private long teacherID;
    private String teacherName;
    private int startLesson;
    private int lessonNum;
    private int day;
    private String room;
    private int studentNum;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createAt;
    private int avai;
    private int remainingQty;

    public RegisteringSubjectClassResponse(Registration registration, SubjectClass subjectClass) {
        if(registration != null) {
            this.id = registration.getId();
            this.userID = registration.getUser().getId();
            this.createAt = registration.getCreateAt();
            this.avai = registration.getAvai();
        } else {
            this.id = Constant.NONE;
            this.userID = Constant.NONE;
            this.createAt = null;
            this.avai = Constant.NONE;
        }

        this.subjectClassID = subjectClass.getId();
        this.team = subjectClass.getTeam();
        this.classID = subjectClass.getClassID();
        this.subjectID = subjectClass.getSubject().getId();
        this.subjectName = subjectClass.getSubject().getName();
        this.subjectCredit = subjectClass.getSubject().getCredit();
        this.subjectGroup = subjectClass.getSubject().getGroup();
        this.teacherID = subjectClass.getId();
        this.teacherName = subjectClass.getTeacher().getName();
        this.startLesson = subjectClass.getStartLesson();
        this.lessonNum = subjectClass.getLessonNum();
        this.day = subjectClass.getDay();
        this.room = subjectClass.getRoom();
        this.studentNum = subjectClass.getStudentNum();
        this.startDate = subjectClass.getStartDate();
        this.endDate = subjectClass.getEndDate();
        this.remainingQty = subjectClass.getRemainingQty();

    }

    public int getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(int remainingQty) {
        this.remainingQty = remainingQty;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getSubjectClassID() {
        return subjectClassID;
    }

    public void setSubjectClassID(long subjectClassID) {
        this.subjectClassID = subjectClassID;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public int getAvai() {
        return avai;
    }

    public void setAvai(int avai) {
        this.avai = avai;
    }

    public int getSubjectCredit() {
        return subjectCredit;
    }

    public void setSubjectCredit(int subjectCredit) {
        this.subjectCredit = subjectCredit;
    }

    public String getSubjectGroup() {
        return subjectGroup;
    }

    public void setSubjectGroup(String subjectGroup) {
        this.subjectGroup = subjectGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(long subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(long teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getStartLesson() {
        return startLesson;
    }

    public void setStartLesson(int startLesson) {
        this.startLesson = startLesson;
    }

    public int getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(int lessonNum) {
        this.lessonNum = lessonNum;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
