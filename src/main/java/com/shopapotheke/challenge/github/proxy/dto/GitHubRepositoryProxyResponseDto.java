package com.shopapotheke.challenge.github.proxy.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubRepositoryProxyResponseDto {

    @JsonProperty("items")
    private List<GitHubRepositoryProxyDto> gitHubRepositoryProxyDtos;
}
