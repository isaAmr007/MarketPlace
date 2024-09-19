package com.example.MarketplaceApplication.java.marketplace.controller;

import com.example.MarketplaceApplication.java.marketplace.dto.UserDTO;
import com.example.MarketplaceApplication.java.marketplace.entity.Role;
import com.example.MarketplaceApplication.java.marketplace.entity.UserEntity;
import com.example.MarketplaceApplication.java.marketplace.repository.UserRepository;
import com.example.MarketplaceApplication.java.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Хэшируем пароль
        user.getRoles().add(new Role("ROLE_USER")); // Добавляем роль
        userRepository.save(user);
        return "User registered successfully!";
    }
}