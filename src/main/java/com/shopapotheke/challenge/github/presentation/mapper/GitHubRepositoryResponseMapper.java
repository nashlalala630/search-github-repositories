package com.shopapotheke.challenge.github.presentation.mapper;

import com.shopapotheke.challenge.github.core.port.domain.GitHubRepositories;
import com.shopapotheke.challenge.github.presentation.dto.GitHubRepositoryDto;
import com.shopapotheke.challenge.github.presentation.dto.GitHubRepositoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GitHubRepositoryResponseMapper {

    public GitHubRepositoryResponseDto map(GitHubRepositories gitHubRepositories) {
        return new GitHubRepositoryResponseDto(gitHubRepositories.getGitHubRepositories().stream().map(dto ->
                GitHubRepositoryDto.builder()
                        .id(dto.getId())
                        .nodeId(dto.getNodeId())
                        .name(dto.getName())
                        .fullName(dto.getFullName())
                        .isPrivateRepo(dto.isPrivateRepo())
                        .description(dto.getDescription())
                        .language(dto.getLanguage())
                        .startCount(dto.getStarCount())
                        .build()
        ).collect(Collectors.toList()));
    }
}
