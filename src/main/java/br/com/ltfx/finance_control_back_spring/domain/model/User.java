package br.com.ltfx.finance_control_back_spring.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private UUID id;
    private String username;
    private String email;
    private String password;

}
