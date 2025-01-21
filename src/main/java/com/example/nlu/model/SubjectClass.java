package com.example.nlu.model;

import com.example.nlu.service.Constant;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "subjectclasses")
public class SubjectClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="team", nullable = false)
    private String team;
    @Column(name="class_id", nullable = false)
    private String classID ;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    @Column(name="start_lesson", nullable = false)
    private int startLesson;
    @Column(name="lesson_num", nullable = false)
    private int lessonNum;
    @Column(name="day", nullable = false)
    private int day;
    @Column(name="room", nullable = false)
    private String room;
    @Column(name="student_num", nullable = false)
    private int studentNum;
    @Column(name="start_date", nullable = false)
    private LocalDate startDate;
    @Column(name="end_date", nullable = false)
    private LocalDate endDate;
    @Column(name="status", nullable = false)
    private int status;
    @Column(name="remaining_qty", nullable = false)
    private int remainingQty;

    public SubjectClass() {
    }

    public int getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(int remainingQty) {
        this.remainingQty = remainingQty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public com.example.nlu.model.Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getStartLesson() {
        return startLesson;
    }

    public void setStartLesson(int lessonStart) {
        this.startLesson = lessonStart;
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

    @Override
    public String toString() {
        return "SubjectClass{" +
                "id=" + id +
                ", team='" + team + '\'' +
                ", classID='" + classID + '\'' +
                ", subject=" + subject +
                ", teacher=" + teacher +
                ", lessonStart=" + startLesson +
                ", lessonNum=" + lessonNum +
                ", day=" + day +
                ", room='" + room + '\'' +
                ", studentNum=" + studentNum +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
