package com.mini.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mini.shopping.config.jwt.JwtTokenProvider;
import com.mini.shopping.dto.LoginRequest;
import com.mini.shopping.dto.LoginResponse;
import com.mini.shopping.dto.RegisterRequest;
import com.mini.shopping.entity.Role;
import com.mini.shopping.entity.User;
import com.mini.shopping.repository.IUserRepository;
import com.mini.shopping.security.CustomerUserDetailService;

@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = customerUserDetailService.loadUserByUsername(user.getUsername());

        String token = jwtTokenProvider.generateToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public User profile(String username) {
        // TODO Auto-generated method stub
        return null;
    }
}
