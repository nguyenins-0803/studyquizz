package com.example.study_quiz_ai.modules.auth.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.core.utils.JwtUtils;
import com.example.study_quiz_ai.modules.auth.dto.LoginDto;
import com.example.study_quiz_ai.modules.auth.dto.LoginResponseDto;
import com.example.study_quiz_ai.modules.auth.dto.RegisterDto;
import com.example.study_quiz_ai.modules.auth.service.AuthService;
import com.example.study_quiz_ai.modules.role.RoleFactory;
import com.example.study_quiz_ai.modules.role.entity.Role;
import com.example.study_quiz_ai.modules.role.exception.RoleNotFoundException;
import com.example.study_quiz_ai.modules.user.UserRepository;
import com.example.study_quiz_ai.modules.user.entity.User;
import com.example.study_quiz_ai.modules.user.exception.UserAlreadyExistsException;
import com.example.study_quiz_ai.modules.user.service.impl.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleFactory roleFactory;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public ApiResponse<?> register(RegisterDto registerDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        User user = createUser(registerDto);

        userRepository.save(user);

        return ApiResponse.builder()
                .success(true)
                .message("User registered successfully")
                .data(user)
                .build();
    }

    @Override
    public ApiResponse<?> login(LoginDto entity) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(entity.getEmail(), entity.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        LoginResponseDto loginDto = LoginResponseDto.builder()
                .token(jwt)
                .type("Bearer")
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();

        return ApiResponse.builder()
                .success(true)
                .message("Login successful")
                .data(loginDto)
                .build();
    }

    private User createUser(RegisterDto registerDto) throws RoleNotFoundException {
        return User.builder()
                .email(registerDto.getEmail())
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .enabled(true)
                .roles(determineRoles(registerDto.getRoles()))
                .build();
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(roleFactory.getInstance("user"));
        } else {
            for (String role : strRoles) {
                roles.add(roleFactory.getInstance(role));
            }
        }
        return roles;
    }
}
