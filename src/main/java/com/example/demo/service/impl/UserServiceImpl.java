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
    public User register(String email, String password, String role) {
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("unique");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        return repo.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = repo.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("not found");
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = repo.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("not found");
        }
        return user;
    }
}
