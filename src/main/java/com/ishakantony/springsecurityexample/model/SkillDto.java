package com.ishakantony.springsecurityexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDto {

    @Null
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 5, max = 400)
    private String description;

    @NotNull
    private SkillEnum difficulty;

    @PositiveOrZero
    private Integer totalQualifiedPeople;

    @PositiveOrZero
    private Integer totalInTrainingPeople;

}
