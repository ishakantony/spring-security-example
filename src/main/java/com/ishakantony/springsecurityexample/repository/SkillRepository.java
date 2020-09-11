package com.ishakantony.springsecurityexample.repository;

import com.ishakantony.springsecurityexample.domain.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SkillRepository extends CrudRepository<Skill, UUID> {
}
