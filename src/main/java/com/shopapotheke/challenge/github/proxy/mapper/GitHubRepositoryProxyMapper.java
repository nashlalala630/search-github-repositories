package com.shopapotheke.challenge.github.proxy.mapper;

import com.shopapotheke.challenge.github.core.domain.GitHubRepositories;
import com.shopapotheke.challenge.github.core.domain.GitHubRepository;
import com.shopapotheke.challenge.github.proxy.dto.GitHubRepositoryProxyResponseDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GitHubRepositoryProxyMapper {

    public GitHubRepositories map(GitHubRepositoryProxyResponseDto gitHubRepositoryProxyResponseDto) {
        return new GitHubRepositories(gitHubRepositoryProxyResponseDto.getGitHubRepositoryProxyDtos().stream().map(dto ->
                GitHubRepository.builder()
                        .id(dto.getId())
                        .nodeId(dto.getNodeId())
                        .name(dto.getName())
                        .fullName(dto.getFullName())
                        .isPrivateRepo(dto.isPrivateRepo())
                        .description(dto.getDescription())
                        .language(dto.getLanguage())
                        .starCount(dto.getStartCount())
                        .htmlUrl(dto.getHtmlUrl())
                        .build()
        ).collect(Collectors.toList()));
    }
}
