package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String email, String password, String role) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("unique");
        }
        User user = new User(email, password, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("not found"));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("not found"));
    }
}
