package com.iconit.tech_be.infrastructure.dto;

import java.time.LocalDateTime;

public record ExceptionDTO (
        String message,
        LocalDateTime timestamp
){
    public ExceptionDTO(String message) {
        this(message, LocalDateTime.now());
    }
}
