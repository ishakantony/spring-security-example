package com.ishakantony.springsecurityexample.controller;

import com.ishakantony.springsecurityexample.model.SkillDto;
import com.ishakantony.springsecurityexample.security.permission.CreateSkillPermission;
import com.ishakantony.springsecurityexample.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/skills")
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillDto>> getSkills() {
        return new ResponseEntity<>(skillService.getSkills(), HttpStatus.OK);
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable("skillId") UUID id) {
        return new ResponseEntity<>(skillService.getSkillById(id), HttpStatus.OK);
    }

    @CreateSkillPermission
    @PostMapping
    public ResponseEntity<SkillDto> createNewSkill(@Valid @RequestBody SkillDto skillDto) {
        return new ResponseEntity<>(skillService.createNewSkill(skillDto), HttpStatus.CREATED);
    }

    @PutMapping("/{skillId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSkill(@PathVariable("skillId") UUID id,
                            @Valid @RequestBody SkillDto skillDto) {
        skillService.updateSkill(id, skillDto);
    }

    @DeleteMapping("/{skillId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkillById(@PathVariable("skillId") UUID id) {
        skillService.deleteSkillById(id);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity httpClientErrorExceptionHandler(HttpClientErrorException ex) {
        return new ResponseEntity(ex.getMessage(), ex.getStatusCode());
    }
}
