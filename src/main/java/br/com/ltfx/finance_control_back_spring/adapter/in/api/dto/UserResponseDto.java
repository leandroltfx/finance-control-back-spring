package br.com.ltfx.finance_control_back_spring.adapter.in.api.dto;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String username,
        String email
) { }
