package com.ltf.financecontrol.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Email e/ou senha inválido(s)");
    }
}
