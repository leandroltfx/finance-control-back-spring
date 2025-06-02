package br.com.ltfx.finance_control_back_spring.adapter.in.api.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Nome de usuário e/ou e-mail já cadastrados.");
    }
}
