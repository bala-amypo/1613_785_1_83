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

    public User register(String email, String password, String role) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(role);
        return repo.save(u);
    }

    public User login(String email, String password) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public User getByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }
}
