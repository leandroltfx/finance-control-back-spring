package com.ltf.financecontrol.modules.account.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;
import com.ltf.financecontrol.modules.account.model.dto.AccountDto;
import com.ltf.financecontrol.modules.account.model.entities.AccountEntity;
import com.ltf.financecontrol.modules.account.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @Tag(name = "Conta bancária")
    @Operation(summary = "Registro de conta bancária", description = "Registrando conta bancária para um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                        @Content(schema = @Schema(implementation = HttpResponseDto.class)
                    )
            }),
            @ApiResponse(responseCode = "422", description = "Essa conta bancária já existe")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<HttpResponseDto> createAccount(
            @RequestBody @Valid AccountDto accountDto,
            HttpServletRequest httpServletRequest
    ) {
        UUID userId = UUID.fromString(httpServletRequest.getAttribute("user_id").toString());
        AccountDto accountDtoCreated = this.accountService.createAccount(accountDto, userId);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage("Conta bancária registrada com sucesso!");
        httpResponseDto.setData(accountDtoCreated);
        return ResponseEntity.status(HttpStatus.OK).body(httpResponseDto);
    }

}
