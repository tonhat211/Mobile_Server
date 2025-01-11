package com.example.nlu.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "initialprograms")
public class InitialProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "major", nullable = false)
    private String major;
    @Column(name = "year", nullable = false)
    private int year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

