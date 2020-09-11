package com.ishakantony.springsecurityexample.repository;

import com.ishakantony.springsecurityexample.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
