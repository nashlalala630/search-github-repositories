package com.shopapotheke.challenge.github.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopapotheke.challenge.github.proxy.config.HttpClientConfiguration;
import com.shopapotheke.challenge.github.core.domain.GitHubRepositories;
import com.shopapotheke.challenge.github.core.port.GitHubPort;
import com.shopapotheke.challenge.github.proxy.dto.GitHubRepositoryProxyDto;
import com.shopapotheke.challenge.github.proxy.dto.GitHubRepositoryProxyResponseDto;
import com.shopapotheke.challenge.github.proxy.mapper.GitHubRepositoryProxyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest
@ContextConfiguration(classes = {GitHubProxy.class, GitHubRepositoryProxyMapper.class, HttpClientConfiguration.class})
public class GitHubProxyIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GitHubPort gitHubPort;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    public void setUp() {
        mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void fetchPopularGitHubRepositories_ifGitHubAPIReturnsValidRepositories_returnsValidRepositories() throws JsonProcessingException {
        mockRestServiceServer.expect(
                once(), requestTo("https://api.github.com/search/repositories?q=created:%3E2019-01-10+language:python&per_page=10&page=1&sort=stars&order=desc"))
                .andRespond(withSuccess(successBody(), MediaType.APPLICATION_JSON));

        GitHubRepositories gitHubRepositories = gitHubPort.fetchPopularGitHubRepositories(10, LocalDate.of(2019, 1, 10), "python");

        mockRestServiceServer.verify();

        assertThat(gitHubRepositories.getGitHubRepositories(), hasSize(1));
        assertThat(gitHubRepositories.getGitHubRepositories().get(0), allOf(
                hasProperty("id", equalTo(1L)),
                hasProperty("name", equalTo("name")),
                hasProperty("nodeId", equalTo("nodeId")),
                hasProperty("description", equalTo("description"))));
    }

    private String successBody() throws JsonProcessingException {
        GitHubRepositoryProxyDto gitHubRepositoryProxyDto = GitHubRepositoryProxyDto.builder()
                .id(1)
                .name("name")
                .nodeId("nodeId")
                .description("description")
                .build();
        return objectMapper.writeValueAsString(new GitHubRepositoryProxyResponseDto(singletonList(gitHubRepositoryProxyDto)));
    }
}
