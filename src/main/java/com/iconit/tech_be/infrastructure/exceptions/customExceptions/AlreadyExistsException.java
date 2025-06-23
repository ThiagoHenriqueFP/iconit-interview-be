package com.iconit.tech_be.infrastructure.exceptions.customExceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(Class<?> clazz) {
        super("The entity " + clazz.getName() + " already exists");
    }
}
