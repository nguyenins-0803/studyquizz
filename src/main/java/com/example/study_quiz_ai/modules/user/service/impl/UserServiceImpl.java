package com.example.study_quiz_ai.modules.user.service.impl;

import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.modules.user.UserRepository;
import com.example.study_quiz_ai.modules.user.entity.User;
import com.example.study_quiz_ai.modules.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
