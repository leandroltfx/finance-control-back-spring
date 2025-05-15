package com.ltf.financecontrol.modules.auth.model.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class CredentialsResponseDto {

    private String access_token;
    private Long expires_in;

}
