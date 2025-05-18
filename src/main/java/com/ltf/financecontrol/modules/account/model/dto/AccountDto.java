package com.ltf.financecontrol.modules.account.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private UUID id;

    @NotBlank(message = "Informe o nome da conta")
    @Schema(example = "Itaú", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(example = "Conta para gastos livres")
    private String description;

    @NotNull(message = "Informe o saldo da conta")
    @Schema(example = "5500.59", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

}
