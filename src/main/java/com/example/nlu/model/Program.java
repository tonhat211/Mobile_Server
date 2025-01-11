package com.example.nlu.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;
    @Column(name="major", nullable = false)
    private String major;
    @Column(name = "subjects_id", nullable = false)
    private String subjectIDs;
    @Column(name="avai", nullable = false)
    private int avai;
    public List<Long> getSubjectIds() {
        if (subjectIDs == null || subjectIDs.isEmpty()) {
            return Collections.emptyList(); // Trả về danh sách rỗng nếu không có dữ liệu
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(subjectIDs, new TypeReference<List<Long>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error parsing subjects JSON", e);
        }
    }

    public String getSubjectIDs() {
        return subjectIDs;
    }

    public void setSubjectIDs(String subjectIDs) {
        this.subjectIDs = subjectIDs;
    }

    public int getAvai() {
        return avai;
    }

    public void setAvai(int avai) {
        this.avai = avai;
    }

    public void setSubjectIds(List<Long> subjectIds) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.subjectIDs = objectMapper.writeValueAsString(subjectIds); // Chuyển danh sách ID thành JSON
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting subject IDs to JSON", e);
        }
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


}
