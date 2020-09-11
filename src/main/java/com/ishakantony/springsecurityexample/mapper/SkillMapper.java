package com.ishakantony.springsecurityexample.mapper;

import com.ishakantony.springsecurityexample.domain.Skill;
import com.ishakantony.springsecurityexample.model.SkillDto;
import org.mapstruct.Mapper;

@Mapper
public interface SkillMapper {

    SkillDto skillToSkillDto(Skill skill);

    Skill skillDtoToSkill(SkillDto skillDto);
}
