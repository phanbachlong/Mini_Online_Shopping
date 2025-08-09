package com.mini.shopping.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mini.shopping.dto.LoginRequest;
import com.mini.shopping.dto.LoginResponse;
import com.mini.shopping.dto.RegisterRequest;
import com.mini.shopping.entity.User;
import com.mini.shopping.service.IAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/v1/api/auth/")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("USER REGISTERED SUCCESSFULLY");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> profile() {
        return ResponseEntity.ok(new User());
    }
}
