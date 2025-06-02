package br.com.ltfx.finance_control_back_spring.adapter.in.api.exception;

import br.com.ltfx.finance_control_back_spring.adapter.in.api.dto.HttpResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(
                new HttpResponseDto<>(
                        "Ocorreu um erro no cadastro de usuário, tente novamente.",
                        errors
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<HttpResponseDto> handleUserFoundException(UserFoundException ex) {
        return new ResponseEntity<>(
                new HttpResponseDto<>(
                        ex.getMessage(),
                        null
                ),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponseDto> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(
                new HttpResponseDto<>(
                        "Ocorreu um erro inesperado, tente novamente.",
                        ex.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
