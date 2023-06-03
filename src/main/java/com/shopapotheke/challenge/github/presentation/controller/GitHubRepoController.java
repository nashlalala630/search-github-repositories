package com.shopapotheke.challenge.github.presentation.controller;

import com.shopapotheke.challenge.github.core.port.GitHubPort;
import com.shopapotheke.challenge.github.presentation.dto.GitHubRepositoryResponseDto;
import com.shopapotheke.challenge.github.presentation.mapper.GitHubRepositoryResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GitHubRepoController {

    private final GitHubPort gitHubPort;
    private final GitHubRepositoryResponseMapper mapper;

    @GetMapping("/search/repositories")
    public ResponseEntity<GitHubRepositoryResponseDto> fetchPopularRepositories(
            @RequestParam(name = "repoNum", required = false) Integer repoNum,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(name = "programmingLang", required = false) String programLanguage) {
        return new ResponseEntity<>(mapper.map(gitHubPort.fetchPopularGitRepositories(repoNum, dateFrom, programLanguage)), HttpStatus.OK);
    }
}
