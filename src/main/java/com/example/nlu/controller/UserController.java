package com.example.nlu.controller;

import com.example.nlu.dto.StudentRequest;
import com.example.nlu.model.*;
import com.example.nlu.repository.BlockRegisterRepository;
import com.example.nlu.repository.StudentRepository;
import com.example.nlu.repository.TeacherRepository;
import jakarta.validation.Valid;
import com.example.nlu.helper.ResourceNotFoundException;
import com.example.nlu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private BlockRegisterRepository blockRegisterRepository;
    @GetMapping("users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("students")
    public List<User> getAllStudents(@RequestBody StudentRequest params) {
        return userRepository.findAll();
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Người dùng này không tồn tại: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId) .orElseThrow(() -> new ResourceNotFoundException("Người dùng này không tồn tại: " + userId));
        user.setEmail(userDetails.getEmail()); user.setName(userDetails.getName());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId) .orElseThrow(() -> new ResourceNotFoundException("Người dùng này không tồn tại: " + userId)); userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>(); response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    @GetMapping("studentalls")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }


    @GetMapping("studentsmin")
    public List<UserMin> getAllStudentMin() {
        List<Student> students = studentRepository.findAll();
        List<Long> blockedIDs = blockRegisterRepository.findBlockedIDs();
        List<UserMin> userMins = new ArrayList<>();
        for(Student s : students) {
            UserMin u = new UserMin(s.getId(),s.getName(),false);
            if(blockedIDs.contains(s.getId())) {
                u.setBlocked(true);
            }
            userMins.add(u);
        }
        return userMins;
    }

    @PostMapping("studentmin")
    public List<UserMin> getStudentMinByID(@RequestBody String userID) {
        System.out.println("getStudentMinByID: " + userID);
        List<Student> students = studentRepository.findByIdLike(userID);
        List<Long> blockedIDs = blockRegisterRepository.findBlockedIDs();
        List<UserMin> userMins = new ArrayList<>();
        for(Student s : students) {
            UserMin u = new UserMin(s.getId(),s.getName(),false);
            if(blockedIDs.contains(s.getId())) {
                u.setBlocked(true);
            }
            userMins.add(u);
        }
        return userMins;
    }

    @PutMapping("grantregister")
    public int grantRegister(@RequestBody long userID) {
        System.out.println("grantregister: "+userID);
        return blockRegisterRepository.deleteByUserId(userID);

    }

    @PutMapping("ungrantregister")
    public BlockRegister unGrantRegister(@RequestBody long userID) {
        System.out.println("ungrantregister:" +userID);
        Student s = new Student();
        s.setId(userID);
        BlockRegister br = new BlockRegister();
        br.setStudent(s);
        return blockRegisterRepository.save(br);

    }





}




