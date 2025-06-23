package com.iconit.tech_be.infrastructure.exceptions.controllerAdvice;

import com.iconit.tech_be.infrastructure.dto.ExceptionDTO;
import com.iconit.tech_be.infrastructure.dto.MultipleExceptionDTO;
import com.iconit.tech_be.infrastructure.exceptions.customExceptions.AlreadyExistsException;
import com.iconit.tech_be.infrastructure.exceptions.customExceptions.NotPersistedEntityException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleAlreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotPersistedEntityException.class)
    public ResponseEntity<ExceptionDTO> handleNotPersistedException(NotPersistedEntityException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<ExceptionDTO> responseEntityExceptionHandler(ResponseStatusException ex) {
        return new ResponseEntity<>(
                new ExceptionDTO(ex.getMessage()), ex.getStatusCode()
        );
    }
       @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<MultipleExceptionDTO> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        MultipleExceptionDTO responseDTO = new MultipleExceptionDTO(errors);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }


}
