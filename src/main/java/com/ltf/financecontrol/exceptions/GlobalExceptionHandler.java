package com.ltf.financecontrol.exceptions;

import com.ltf.financecontrol.dto.HttpResponseDto;

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
        httpResponseDto.addMessage("Email e/ou senha inválidos.");
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

}
