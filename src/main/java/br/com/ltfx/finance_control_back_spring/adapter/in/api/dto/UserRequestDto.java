package br.com.ltfx.finance_control_back_spring.adapter.in.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record UserRequestDto(
        @NotBlank(message = "Informe o nome de usuário")
        @Size(min = 3, message = "O nome de usuário deve conter no mínimo 3 caracteres")
        @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*$", message = "O nome de usuário deve começar com letra e conter apenas letras e números, sem espaços ou caracteres especiais.")
        String username,

        @NotBlank(message = "Informe o e-mail")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Informe a senha")
        @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
        String password
) { }
