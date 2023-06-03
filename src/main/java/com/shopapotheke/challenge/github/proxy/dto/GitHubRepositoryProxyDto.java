package com.shopapotheke.challenge.github.proxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubRepositoryProxyDto {

    private long id;
    @JsonProperty("node_id")
    private String nodeId;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("private")
    private boolean isPrivateRepo;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String description;
    private String language;
    @JsonProperty("stargazers_count")
    private String startCount;
}
