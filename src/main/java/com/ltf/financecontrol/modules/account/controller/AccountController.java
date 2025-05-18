package com.ltf.financecontrol.modules.account.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;
import com.ltf.financecontrol.modules.account.model.dto.AccountDto;
import com.ltf.financecontrol.modules.account.model.dto.AccountPatchDto;
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

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
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

    @GetMapping
    public ResponseEntity<HttpResponseDto> getAccounts(
            @PageableDefault(page = 0, size = 5, direction = Sort.Direction.ASC, sort = "name") Pageable pageable
    ) {
        List<AccountDto> listAccounts = this.accountService.getAccounts(pageable);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setData(listAccounts);
        return ResponseEntity.status(HttpStatus.OK).body(httpResponseDto);
    }

    @GetMapping("filter")
    public ResponseEntity<HttpResponseDto> filterAccounts(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String description,
            @RequestParam(required = false, defaultValue = "0") BigDecimal amount,
            @PageableDefault(page = 0, size = 5, direction = Sort.Direction.ASC, sort = "name") Pageable pageable
    ) {
        List<AccountDto> listAccounts = this.accountService.filterAccounts(pageable, name, description, amount);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setData(listAccounts);
        return ResponseEntity.status(HttpStatus.OK).body(httpResponseDto);
    }

    @PutMapping()
    public ResponseEntity<HttpResponseDto> updateAccount(
        @RequestBody @Valid AccountDto accountDto,
        HttpServletRequest httpServletRequest
    ) {
        UUID userId = UUID.fromString(httpServletRequest.getAttribute("user_id").toString());
        AccountDto accountDtoUpdated = this.accountService.updateAccount(accountDto, userId);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage("Conta bancária alterada com sucesso!");
        httpResponseDto.setData(accountDtoUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(httpResponseDto);
    }

    @PatchMapping()
    public ResponseEntity<HttpResponseDto> updateAccount(
            @RequestBody @Valid AccountPatchDto accountPatchDto,
            HttpServletRequest httpServletRequest
    ) {
        UUID userId = UUID.fromString(httpServletRequest.getAttribute("user_id").toString());
        AccountDto accountDtoUpdated = this.accountService.updatePartialAccount(accountPatchDto, userId);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage("Conta bancária alterada com sucesso!");
        httpResponseDto.setData(accountDtoUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(httpResponseDto);
    }

}
