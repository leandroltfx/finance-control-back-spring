package br.com.ltfx.finance_control_back_spring.adapter.in.api.dto;

public record HttpResponseDto<T>(
        String message,
        T data
) { }
