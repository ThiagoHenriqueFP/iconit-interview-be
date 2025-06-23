package com.iconit.tech_be.infrastructure.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MultipleExceptionDTO(
        List<String> message,
        LocalDateTime timestamp
){
    public MultipleExceptionDTO(List<String> message) {
        this(message, LocalDateTime.now());
    }
}
