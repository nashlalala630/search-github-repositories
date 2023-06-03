package com.shopapotheke.challenge.github.core.port;

import com.shopapotheke.challenge.github.core.port.domain.GitHubRepositories;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public interface GitHubPort {

    GitHubRepositories fetchPopularGitRepositories(Integer repoNum, LocalDate dateFrom, String programmingLanguage);
}
