package com.example.MarketplaceApplication.java.marketplace.repository;
import com.example.MarketplaceApplication.java.marketplace.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}