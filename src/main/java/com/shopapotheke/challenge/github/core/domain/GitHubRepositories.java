package com.shopapotheke.challenge.github.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubRepositories {
    private List<GitHubRepository> gitHubRepositories;
}
