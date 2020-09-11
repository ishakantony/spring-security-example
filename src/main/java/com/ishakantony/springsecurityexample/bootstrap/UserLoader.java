package com.ishakantony.springsecurityexample.bootstrap;

import com.ishakantony.springsecurityexample.domain.Authority;
import com.ishakantony.springsecurityexample.domain.Role;
import com.ishakantony.springsecurityexample.domain.User;
import com.ishakantony.springsecurityexample.repository.AuthorityRepository;
import com.ishakantony.springsecurityexample.repository.RoleRepository;
import com.ishakantony.springsecurityexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserLoader implements CommandLineRunner {
    private final AuthorityRepository authorityRepository;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private void loadUsers() {
        Authority viewAllSkillsPermission = authorityRepository.save(Authority.builder().permission("skills.v1.view.all").build());
        Authority viewOneSkillsPermission = authorityRepository.save(Authority.builder().permission("skills.v1.view.one").build());
        Authority createSkillPermission = authorityRepository.save(Authority.builder().permission("skills.v1.create").build());
        Authority updateSkillPermission = authorityRepository.save(Authority.builder().permission("skills.v1.update").build());
        Authority deleteSkillPermission = authorityRepository.save(Authority.builder().permission("skills.v1.delete").build());

        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());
        Role managerRole = roleRepository.save(Role.builder().name("MANAGER").build());

        adminRole.setAuthorities(new HashSet<>(Set.of(viewAllSkillsPermission, viewOneSkillsPermission, createSkillPermission, updateSkillPermission, deleteSkillPermission)));

        userRole.setAuthorities(new HashSet<>(Set.of(viewAllSkillsPermission, viewOneSkillsPermission, createSkillPermission, updateSkillPermission)));

        managerRole.setAuthorities(new HashSet<>(Set.of(viewAllSkillsPermission, viewOneSkillsPermission, deleteSkillPermission)));

        roleRepository.saveAll(Arrays.asList(adminRole, userRole, managerRole));

        userRepository.save(User.builder().username("admin").password(passwordEncoder.encode("admin")).role(adminRole).build());
        userRepository.save(User.builder().username("user").password(passwordEncoder.encode("user")).role(userRole).build());
        userRepository.save(User.builder().username("manager").password(passwordEncoder.encode("manager")).role(managerRole).build());

        System.out.println("LOADED USERS: " + userRepository.count());
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }
}
