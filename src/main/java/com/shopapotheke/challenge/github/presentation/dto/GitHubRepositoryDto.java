package com.shopapotheke.challenge.github.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GitHubRepositoryDto {

    private long id;
    private String nodeId;
    private String name;
    private String fullName;
    private boolean privateRepo;
    private String htmlUrl;
    private String description;
    private String language;
    private int startCount;
}
