package com.learning.blogpost.service;

import com.learning.blogpost.dto.LoginRequest;
import com.learning.blogpost.dto.RegisterRequest;
import com.learning.blogpost.model.User;
import com.learning.blogpost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        User savedUser = userRepository.save(user);
        return user;
    }

    public void login(LoginRequest loginRequest) {
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentuser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.of(principal);

    }
}
