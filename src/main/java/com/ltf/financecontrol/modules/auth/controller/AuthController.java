package com.ltf.financecontrol.modules.auth.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;
import com.ltf.financecontrol.modules.auth.model.dto.CredentialsResponseDto;
import com.ltf.financecontrol.modules.auth.service.AuthService;
import com.ltf.financecontrol.modules.auth.model.dto.CredentialsDto;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> login(
            @RequestBody @Valid CredentialsDto credentialsDto
    ) {
        CredentialsResponseDto credentialsResponseDto = this.authService.authenticated(credentialsDto);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage("Login efetuado com sucesso!");
        httpResponseDto.setData(credentialsResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(httpResponseDto);
    }

}
