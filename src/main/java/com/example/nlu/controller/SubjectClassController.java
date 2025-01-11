package com.example.nlu.controller;

import com.example.nlu.dto.SubjectClassRequest;
import com.example.nlu.dto.SubjectClassResponse;
import com.example.nlu.model.SubjectClass;
import com.example.nlu.repository.SubjectClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/subjectclass")
public class SubjectClassController {
    @Autowired
    private SubjectClassRepository subjectClassRepository;

    @GetMapping("/subjectclasses")
    public List<SubjectClass> findAll() {
        return subjectClassRepository.findAll();  // Gọi tới phương thức trong Service để lấy dữ liệu
    }

    @PostMapping("/subjectclass")
    public SubjectClassResponse findByID(@RequestBody SubjectClassRequest params) {
        System.out.println("get subjectclass by ID: " + params.getSubjectClassID());
        Optional<SubjectClass> subjectClass = subjectClassRepository.findById(params.getSubjectClassID());
        if (!subjectClass.isPresent()) {
            return null;
        }
        SubjectClassResponse response = new SubjectClassResponse(subjectClass.get());
        return response;
    }

}
