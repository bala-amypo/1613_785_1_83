package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwt;

    public AuthController(UserService userService) {
        this.userService = userService;
        this.jwt = new JwtTokenProvider("secret", 3600000);
    }

    @PostMapping("/register")
    public User register(@RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String role) {
        return userService.register(email, password, role);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        userService.login(email, password);
        return jwt.createToken(email);
    }
}
