package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String email, String password, String role) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with this email already exists (unique).");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // plain text password (demo only)
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new IllegalStateException("User not found.");
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

        return user;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found."));
    }
}
