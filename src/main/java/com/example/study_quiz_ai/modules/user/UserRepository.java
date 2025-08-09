package com.example.study_quiz_ai.modules.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study_quiz_ai.modules.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}