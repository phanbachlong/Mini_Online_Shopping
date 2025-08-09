package com.mini.shopping.service;

import com.mini.shopping.dto.LoginRequest;
import com.mini.shopping.dto.LoginResponse;
import com.mini.shopping.dto.RegisterRequest;
import com.mini.shopping.entity.User;

public interface IAuthService {

    void register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    User profile(String username);
}
