package com.shopapotheke.challenge.github.proxy;

import com.shopapotheke.challenge.github.core.port.GitHubPort;
import com.shopapotheke.challenge.github.core.domain.GitHubRepositories;
import com.shopapotheke.challenge.github.proxy.dto.GitHubRepositoryProxyResponseDto;
import com.shopapotheke.challenge.github.proxy.mapper.GitHubRepositoryProxyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@Service
public class GitHubProxy implements GitHubPort {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;
    private static final String GITHUB_URI = "/search/repositories";
    private final String gitHubEndpoint;
    private final RestTemplate restTemplate;
    private final GitHubRepositoryProxyMapper mapper;

    public GitHubProxy(RestTemplate restTemplate, @Value("${github.endpoint}") String githubEndpoint, GitHubRepositoryProxyMapper mapper) {
        this.restTemplate = restTemplate;
        this.gitHubEndpoint = githubEndpoint;
        this.mapper = mapper;
    }

    @Override
    public GitHubRepositories fetchPopularGitHubRepositories(Integer repoNum, LocalDate dateFrom, String programmingLanguage) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(this.gitHubEndpoint + GITHUB_URI);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        if (dateFrom != null) {
            String value = "created:>" + dateFrom.format(DATE_TIME_FORMATTER);
            log.debug("query value for date: [{}].", value);
            queryParams.add("q", value);
        }

        if (programmingLanguage != null) {
            if (queryParams.get("q") == null) {
                queryParams.add("language", programmingLanguage);
            } else {
                queryParams.set("q", queryParams.getFirst("q") + "+language:" + programmingLanguage);
            }
        }

        if (repoNum != null) {
            queryParams.add("per_page", String.valueOf(repoNum));
            queryParams.add("page", "1");
        }

        queryParams.add("sort", "stars");
        queryParams.add("order", "desc");

        uriBuilder.queryParams(queryParams);
        uriBuilder.build();

        log.debug("Url to GitHub: {}.", uriBuilder.toUriString());

        GitHubRepositoryProxyResponseDto gitHubRepositoryProxyResponseDtoResponseEntity =
                restTemplate.getForObject(uriBuilder.toUriString(), GitHubRepositoryProxyResponseDto.class, entity);
        return mapper.map(Objects.requireNonNull(gitHubRepositoryProxyResponseDtoResponseEntity));
    }
}
