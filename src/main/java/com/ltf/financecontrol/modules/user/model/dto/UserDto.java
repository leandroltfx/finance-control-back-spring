package com.ltf.financecontrol.modules.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Informe o nome de usuário")
    @Pattern(message = "O nome de usuário não pode conter caracteres especiais ou espaços em branco", regexp = "^[a-zA-Z0-9]+$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String username;

    @NotBlank(message = "Informe o email")
    @Email(message = "Digite o email corretamente", regexp = "^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotBlank(message = "Informe a senha")
    private String password;

}
