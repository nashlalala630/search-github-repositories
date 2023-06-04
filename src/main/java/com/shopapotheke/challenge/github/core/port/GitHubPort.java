package com.shopapotheke.challenge.github.core.port;

import com.shopapotheke.challenge.github.core.domain.GitHubRepositories;

import java.time.LocalDate;

public interface GitHubPort {

    GitHubRepositories fetchPopularGitHubRepositories(Integer repoNum, LocalDate dateFrom, String programmingLanguage);
}
