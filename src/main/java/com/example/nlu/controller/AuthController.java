package com.example.nlu.controller;

import com.example.nlu.dto.LoginRequest;
import com.example.nlu.dto.LoginResponse;
import com.example.nlu.model.User;
import com.example.nlu.repository.UserRepository;
import com.example.nlu.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("login");
        User user = userRepository.findById(loginRequest.getId());
        if (user != null && user.getPwd().equals(loginRequest.getPassword())) {
            String token = JwtUtils.generateToken(user.getId());
            LoginResponse loginResponse = new LoginResponse(user.getId(), user.getName(), token);
            return ResponseEntity.ok(loginResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
