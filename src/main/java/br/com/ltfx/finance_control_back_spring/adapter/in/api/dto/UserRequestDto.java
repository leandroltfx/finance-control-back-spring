package br.com.ltfx.finance_control_back_spring.adapter.in.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank String username,
        @Email String email,
        @NotBlank String password
) { }
