package com.shopapotheke.challenge.github.presentation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessageDto {
    private LocalDateTime timestamp;
    private String message;
}
