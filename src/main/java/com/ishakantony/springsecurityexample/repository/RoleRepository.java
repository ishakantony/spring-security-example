package com.ishakantony.springsecurityexample.repository;

import com.ishakantony.springsecurityexample.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
