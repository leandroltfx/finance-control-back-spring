package com.ltf.financecontrol.modules.auth.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;
import com.ltf.financecontrol.modules.auth.model.dto.CredentialsResponseDto;
import com.ltf.financecontrol.modules.auth.service.AuthService;
import com.ltf.financecontrol.modules.auth.model.dto.CredentialsDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Login")
    @Operation(summary = "Autenticação de usuário", description = "Autenticando um usuário no sistema")
    @ApiResponses(
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = HttpResponseDto.class)
                    )
            })
    )
    public ResponseEntity<HttpResponseDto> login(
            @RequestBody @Valid CredentialsDto credentialsDto
    ) {
        CredentialsResponseDto credentialsResponseDto = this.authService.authenticated(credentialsDto);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage("Login efetuado com sucesso!");
        httpResponseDto.setData(credentialsResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(httpResponseDto);
    }

}
