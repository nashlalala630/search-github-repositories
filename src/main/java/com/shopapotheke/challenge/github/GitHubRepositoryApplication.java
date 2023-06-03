package com.shopapotheke.challenge.github;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class GitHubRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitHubRepositoryApplication.class, args);
	}
}
