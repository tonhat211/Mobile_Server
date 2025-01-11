package com.example.nlu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "initialprogramdetails")
public class InitialProgramDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "initial_program_id", nullable = false)
    private long initialProgramID;
    @Column(name = "semester_id", nullable = false)
    private long semesterID;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public long getInitialProgramID() {
        return initialProgramID;
    }

    public void setInitialProgramID(long initialProgramID) {
        this.initialProgramID = initialProgramID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(long semesterID) {
        this.semesterID = semesterID;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}

