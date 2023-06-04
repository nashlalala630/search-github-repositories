package com.shopapotheke.challenge.github.presentation.controller;

import com.shopapotheke.challenge.github.core.domain.GitHubRepositories;
import com.shopapotheke.challenge.github.core.domain.GitHubRepository;
import com.shopapotheke.challenge.github.core.port.GitHubPort;
import com.shopapotheke.challenge.github.presentation.mapper.GitHubRepositoryResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GitHubRepoController.class)
@ContextConfiguration(classes =
        {GitHubRepoControllerIT.GitHubRepoControllerITConfig.class, GitHubRepoController.class, GitHubRepositoryResponseMapper.class})
public class GitHubRepoControllerIT {

    @TestConfiguration
    public static class GitHubRepoControllerITConfig {
        @Bean
        public GitHubPort gitHubPort() {
            GitHubPort gitHubPort = mock(GitHubPort.class);
            when(gitHubPort.fetchPopularGitHubRepositories(anyInt(), any(LocalDate.class), anyString())).thenReturn(createGitHubRepositories());
            return gitHubPort;
        }

        private GitHubRepositories createGitHubRepositories() {
            GitHubRepository gitHubRepository1 = GitHubRepository.builder()
                    .id(1)
                    .isPrivateRepo(true)
                    .name("name1")
                    .description("desc1")
                    .starCount(1)
                    .fullName("fullName1")
                    .htmlUrl("htmlUrl1")
                    .language("python")
                    .nodeId("nodeId1")
                    .build();
            GitHubRepository gitHubRepository2 = GitHubRepository.builder()
                    .id(2)
                    .isPrivateRepo(false)
                    .name("name2")
                    .description("desc2")
                    .starCount(2)
                    .fullName("fullName2")
                    .htmlUrl("htmlUrl2")
                    .language("java")
                    .nodeId("nodeId2")
                    .build();
            return new GitHubRepositories(Arrays.asList(gitHubRepository1, gitHubRepository2));
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void fetchPopularRepositories_ifRepositoriesFound_returnsFoundRepositories() throws Exception {
        mockMvc.perform(
                get("/search/repositories?repoNum=10&dateFrom=2022-02-02&programmingLang=python")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.repositories[0].id").value(1))
                .andExpect(jsonPath("$.repositories[0].privateRepo").value(true))
                .andExpect(jsonPath("$.repositories[0].name").value("name1"))
                .andExpect(jsonPath("$.repositories[0].description").value("desc1"))
                .andExpect(jsonPath("$.repositories[0].startCount").value("1"))
                .andExpect(jsonPath("$.repositories[0].fullName").value("fullName1"))
                .andExpect(jsonPath("$.repositories[0].htmlUrl").value("htmlUrl1"))
                .andExpect(jsonPath("$.repositories[0].language").value("python"))
                .andExpect(jsonPath("$.repositories[0].nodeId").value("nodeId1"))
                .andExpect(jsonPath("$.repositories[1].id").value(2))
                .andExpect(jsonPath("$.repositories[1].privateRepo").value(false))
                .andExpect(jsonPath("$.repositories[1].name").value("name2"))
                .andExpect(jsonPath("$.repositories[1].description").value("desc2"))
                .andExpect(jsonPath("$.repositories[1].startCount").value("2"))
                .andExpect(jsonPath("$.repositories[1].fullName").value("fullName2"))
                .andExpect(jsonPath("$.repositories[1].htmlUrl").value("htmlUrl2"))
                .andExpect(jsonPath("$.repositories[1].language").value("java"))
                .andExpect(jsonPath("$.repositories[1].nodeId").value("nodeId2"));
    }

    @Test
    public void fetchPopularRepositories_ifInvalidQueryParam_returnsClientError() throws Exception {

    }

    @Test
    public void fetchPopularRepositories_ifNoRepositoryFound_returnsEmptyResult() throws Exception {

    }

    @Test
    public void fetchPopularRepositories_ifServiceError_returnsServerInternalError() throws Exception {

    }
}
