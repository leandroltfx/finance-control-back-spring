package com.ltf.financecontrol.exceptions;

import com.ltf.financecontrol.dto.HttpResponseDto;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

// Intercepta exceções lançadas dentro de métodos anotados com @RequestMapping, @GetMapping, @PostMapping, etc.
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException ex) {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(httpResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String[] errors = new String[fieldErrors.size()];
        for (FieldError fieldError : fieldErrors) {
            httpResponseDto.addMessage(fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpResponseDto);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> handleUserFoundException(UserFoundException ex) {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(httpResponseDto);
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<?> handleDataAccessResourceFailureException(DataAccessResourceFailureException ex) {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpResponseDto);
    }

}
