package com.example.study_quiz_ai.modules.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study_quiz_ai.modules.role.entity.Role;
import com.example.study_quiz_ai.modules.role.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
