package com.example.nlu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "semesterscores")
public class SemesterScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY) // Chỉ tải Student khi cần
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY) // Chỉ tải Semester khi cần
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;
    @Column(name="avg_score_10", nullable = false)
    private double avgScore10;
    @Column(name="avg_score_4", nullable = false)
    private double avgScore4;
    @Column(name="reached_credit", nullable = false)
    private int reachedCredit;

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

    public double getAvgScore10() {
        return avgScore10;
    }

    public void setAvgScore10(double avgScore10) {
        this.avgScore10 = avgScore10;
    }

    public double getAvgScore4() {
        return avgScore4;
    }

    public void setAvgScore4(double avgScore4) {
        this.avgScore4 = avgScore4;
    }

    public int getReachedCredit() {
        return reachedCredit;
    }

    public void setReachedCredit(int reachCredit) {
        this.reachedCredit = reachCredit;
    }
}
