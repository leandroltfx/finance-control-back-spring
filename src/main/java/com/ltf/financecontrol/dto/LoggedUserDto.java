package com.ltf.financecontrol.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoggedUserDto {

    private String id;
    private String username;
    private String email;
    private String message;

}
