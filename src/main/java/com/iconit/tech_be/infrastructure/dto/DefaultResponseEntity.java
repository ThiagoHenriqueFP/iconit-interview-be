package com.iconit.tech_be.infrastructure.dto;

import java.time.LocalDateTime;

public record DefaultResponseEntity<T>(
        T data,
        LocalDateTime timestamp
) {
    public DefaultResponseEntity(T data) {
        this(data, LocalDateTime.now());
    }
}
