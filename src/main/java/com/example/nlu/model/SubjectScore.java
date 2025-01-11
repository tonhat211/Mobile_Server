package com.example.nlu.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "subjectscores")
public class SubjectScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @Column(name="exam_score", nullable = false)
    private double examScore;
    @Column(name="score_10", nullable = false)
    private double score10;
    @Column(name="score_4", nullable = false)
    private double score4;
    @Column(name="score_c", nullable = false)
    private char scoreC;
    @Column(name="result", nullable = false)
    private int result;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getExamScore() {
        return examScore;
    }

    public void setExamScore(double examScore) {
        this.examScore = examScore;
    }

    public double getScore10() {
        return score10;
    }

    public void setScore10(double score10) {
        this.score10 = score10;
    }

    public double getScore4() {
        return score4;
    }

    public void setScore4(double score4) {
        this.score4 = score4;
    }

    public char getScoreC() {
        return scoreC;
    }

    public void setScoreC(char scoreC) {
        this.scoreC = scoreC;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
