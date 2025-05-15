package com.ltf.financecontrol.modules.auth.model.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDto {

    @NotBlank(message = "Informe o email")
    private String email;

    @NotBlank(message = "Informe a senha")
    private String password;

}
