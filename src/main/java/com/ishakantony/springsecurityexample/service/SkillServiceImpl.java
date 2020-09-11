package com.ishakantony.springsecurityexample.service;

import com.ishakantony.springsecurityexample.domain.Skill;
import com.ishakantony.springsecurityexample.mapper.SkillMapper;
import com.ishakantony.springsecurityexample.model.SkillDto;
import com.ishakantony.springsecurityexample.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    private final SkillMapper skillMapper;

    @Override
    public List<SkillDto> getSkills() {
        List<SkillDto> skillDtos = new ArrayList<>();

        skillRepository.findAll().iterator().forEachRemaining(skill -> {
            skillDtos.add(skillMapper.skillToSkillDto(skill));
        });
        return skillDtos;
    }

    @Override
    public SkillDto getSkillById(UUID id) {
        return skillMapper.skillToSkillDto(skillRepository.findById(id).orElseThrow(() -> {
            return new HttpClientErrorException(HttpStatus.NOT_FOUND, "Skill with ID: " + id + " doesn't exists");
        }));
    }

    @Override
    public SkillDto createNewSkill(SkillDto skillDto) {
        return skillMapper.skillToSkillDto(skillRepository.save(skillMapper.skillDtoToSkill(skillDto)));
    }

    @Override
    public void updateSkill(UUID id, SkillDto skillDto) {
        Skill skillToBeUpdated = skillRepository.findById(id).orElseThrow(() -> {
            return new HttpClientErrorException(HttpStatus.NOT_FOUND, "Skill with ID: " + id + " doesn't exists");
        });

        skillToBeUpdated.setName(skillDto.getName());
        skillToBeUpdated.setDescription(skillDto.getDescription());
        skillToBeUpdated.setDifficulty(skillDto.getDifficulty().name());
        skillToBeUpdated.setTotalQualifiedPeople(skillDto.getTotalQualifiedPeople());
        skillToBeUpdated.setTotalInTrainingPeople(skillDto.getTotalInTrainingPeople());

        skillRepository.save(skillToBeUpdated);
    }

    @Override
    public void deleteSkillById(UUID id) {
        Skill skillToBeDeleted = skillRepository.findById(id).orElseThrow(() -> {
            return new HttpClientErrorException(HttpStatus.NOT_FOUND, "Skill with ID: " + id + " doesn't exists");
        });

        skillRepository.delete(skillToBeDeleted);
    }
}
