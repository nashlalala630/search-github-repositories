package com.shopapotheke.challenge.github.core.port.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GitHubRepository {

    private long id;
    private String nodeId;
    private String name;
    private String fullName;
    private boolean isPrivateRepo;
    private String htmlUrl;
    private String description;
    private String language;
    private String starCount;
}
