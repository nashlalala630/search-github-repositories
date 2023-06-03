package com.shopapotheke.challenge.github.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubRepositoryResponseDto {
    private List<GitHubRepositoryDto> gitHubRepositoryDtos;
}
