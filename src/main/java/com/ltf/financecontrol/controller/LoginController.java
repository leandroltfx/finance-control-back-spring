package com.ltf.financecontrol.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;
import com.ltf.financecontrol.dto.LoginDto;
import com.ltf.financecontrol.dto.LoggedUserDto;
import com.ltf.financecontrol.exceptions.InvalidCredentialsException;
import com.ltf.financecontrol.model.User;
import com.ltf.financecontrol.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginDto loginDto
    ) throws InvalidCredentialsException {
        LoggedUserDto loggedUserDto = null;
        List<User> users = this.userRepository.findAll();

        for (User user : users) {
            if (user.getEmail().equals(loginDto.getEmail()) && user.getPassword().equals(loginDto.getPassword())) {
                loggedUserDto = new LoggedUserDto();
                loggedUserDto.setId(user.getId());
                loggedUserDto.setUsername(user.getUsername());
                loggedUserDto.setEmail(user.getEmail());
            }
        }
        if (loggedUserDto == null) {
            throw new InvalidCredentialsException("Email e/ou senha inválidos");
        }

        //"Login efetuado com sucesso!"
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage("Login efetuado com sucesso!");
        httpResponseDto.setData(loggedUserDto);
        return ResponseEntity.status(200).body(httpResponseDto);
    }

}
