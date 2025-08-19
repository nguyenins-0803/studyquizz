package com.example.study_quiz_ai.modules.user.service;

import com.example.study_quiz_ai.modules.user.entity.User;

public interface UserService {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void save(User user);
}