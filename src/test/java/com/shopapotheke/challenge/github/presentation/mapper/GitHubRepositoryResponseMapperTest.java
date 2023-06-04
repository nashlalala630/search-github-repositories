package com.shopapotheke.challenge.github.presentation.mapper;

import com.shopapotheke.challenge.github.core.domain.GitHubRepositories;
import com.shopapotheke.challenge.github.core.domain.GitHubRepository;
import com.shopapotheke.challenge.github.presentation.dto.GitHubRepositoryDto;
import com.shopapotheke.challenge.github.presentation.dto.GitHubRepositoryResponseDto;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

public class GitHubRepositoryResponseMapperTest {

    @Test
    public void map_ifDomainObjectsAreAvailableAndValid_returnsResponseDto() {
        // given: valid domain objects
        GitHubRepositories gitHubRepositories = new GitHubRepositories(
                asList(
                        GitHubRepository.builder()
                                .id(1L)
                                .name("name1")
                                .description("desc1")
                                .language("python")
                                .nodeId("nodeId1")
                                .build(),
                        GitHubRepository.builder()
                                .id(2L)
                                .name("name2")
                                .description("desc2")
                                .language("python")
                                .htmlUrl("htmlUrl2")
                                .build()
                )
        );

        // when: map from domain object to dto
        GitHubRepositoryResponseDto gitHubRepositoryResponseDto = new GitHubRepositoryResponseMapper().map(gitHubRepositories);

        // then: should return mapped dto
        assertThat(gitHubRepositoryResponseDto.getGitHubRepositoryDtos(), hasSize(2));
        assertThat(gitHubRepositoryResponseDto.getGitHubRepositoryDtos(), containsInAnyOrder(
                GitHubRepositoryDto.builder()
                        .id(1L)
                        .name("name1")
                        .description("desc1")
                        .language("python")
                        .nodeId("nodeId1")
                        .build(),
                GitHubRepositoryDto.builder()
                        .id(2L)
                        .name("name2")
                        .description("desc2")
                        .language("python")
                        .htmlUrl("htmlUrl2")
                        .build()
        ));
    }
}
