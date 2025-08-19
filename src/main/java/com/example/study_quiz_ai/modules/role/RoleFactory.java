package com.example.study_quiz_ai.modules.role;

import org.springframework.stereotype.Component;

import com.example.study_quiz_ai.modules.role.entity.Role;
import com.example.study_quiz_ai.modules.role.enums.ERole;
import com.example.study_quiz_ai.modules.role.exception.RoleNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleFactory {
    private final RoleRepository roleRepository;

    public Role getInstance(String role) throws RoleNotFoundException {
        switch (role) {
            case "admin":
                return roleRepository.findByName(ERole.ROLE_ADMIN);
            case "user":
                return roleRepository.findByName(ERole.ROLE_USER);
            case "moderator":
                return roleRepository.findByName(ERole.ROLE_MODERATOR);
            case "super_admin":
                return roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
            default:
                throw new RoleNotFoundException("Role not found: " + role);
        }
    }
}
