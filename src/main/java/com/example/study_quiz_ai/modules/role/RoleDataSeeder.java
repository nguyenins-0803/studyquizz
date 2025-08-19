package com.example.study_quiz_ai.modules.role;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.study_quiz_ai.modules.role.entity.Role;
import com.example.study_quiz_ai.modules.role.enums.ERole;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleDataSeeder {
    private final RoleRepository roleRepository;

    @EventListener
    @Transactional
    public void LoadRoles(ContextRefreshedEvent event) {
        List<ERole> roles = Arrays.stream(ERole.values()).toList();

        for (ERole erole : roles) {
            if (roleRepository.findByName(erole) == null) {
                roleRepository.save(new Role(erole));
            }
        }
    }
}
