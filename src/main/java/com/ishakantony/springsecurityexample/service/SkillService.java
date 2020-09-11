package com.ishakantony.springsecurityexample.service;

import com.ishakantony.springsecurityexample.model.SkillDto;

import java.util.List;
import java.util.UUID;

public interface SkillService {
    List<SkillDto> getSkills();

    SkillDto getSkillById(UUID id);

    SkillDto createNewSkill(SkillDto skillDto);

    void updateSkill(UUID id, SkillDto skillDto);

    void deleteSkillById(UUID id);
}
