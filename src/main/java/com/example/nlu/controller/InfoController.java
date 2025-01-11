package com.example.nlu.controller;

import com.example.nlu.helper.ResourceNotFoundException;
import com.example.nlu.model.Student;
import com.example.nlu.model.User;
import com.example.nlu.repository.StudentRepository;
import com.example.nlu.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/info")
public class InfoController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("student")
    public Student getStudentInfo(@RequestBody long id) {
        System.out.println("getStudentInfo: " + id);
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return student.get();
        }
        return null;
    }
}




