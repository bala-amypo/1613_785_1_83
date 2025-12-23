package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User register(User user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return repo.save(user);
    }

    @Override
    public User login(String email, String password) {
        User u = repo.findByEmail(email);
        if (u == null || !u.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return u;
    }
}
