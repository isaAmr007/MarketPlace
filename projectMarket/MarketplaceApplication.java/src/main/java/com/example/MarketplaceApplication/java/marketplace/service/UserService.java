package com.example.MarketplaceApplication.java.marketplace.service;

import com.example.MarketplaceApplication.java.marketplace.dto.UserDTO;
import com.example.MarketplaceApplication.java.marketplace.entity.UserEntity;
import com.example.MarketplaceApplication.java.marketplace.exception.UserAlreadyExistsException;
import com.example.MarketplaceApplication.java.marketplace.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(UserEntity user) {
        logger.info("Начинаем процесс создания пользователя: {}");
        try {
            UserEntity savedUser = userRepository.save(user);
            logger.info("Пользователь успешно создан: {}");
            return savedUser;
        } catch (Exception e) {
            logger.log(Level.parse("Ошибка при создании пользователя: {}"), e.getMessage());
            throw e;
        }
    }

    public void registerUser(UserDTO userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
    }



}
