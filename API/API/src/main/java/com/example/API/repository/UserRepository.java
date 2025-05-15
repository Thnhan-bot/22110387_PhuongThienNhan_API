package com.example.API.repository;

import com.example.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Tìm kiếm theo username
    boolean existsByUsername(String username);
    // Tìm kiếm theo email
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}



