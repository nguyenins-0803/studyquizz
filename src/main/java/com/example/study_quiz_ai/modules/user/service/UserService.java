package com.example.study_quiz_ai.modules.user.service;

import com.example.study_quiz_ai.modules.user.entity.User;

public interface UserService {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}