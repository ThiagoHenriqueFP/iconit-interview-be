package com.iconit.tech_be.infrastructure.exceptions.customExceptions;

public class NotPersistedEntityException extends RuntimeException {
    public NotPersistedEntityException(Class<?> clazz, Long id) {
        super("Entity: " + clazz.getName() + " with id: " + id + " not found in database");
    }

    public NotPersistedEntityException(Class<?> clazz, String code) {
        super("Entity: " + clazz.getName() + " with code: " + code + " not found in database");
    }
}
