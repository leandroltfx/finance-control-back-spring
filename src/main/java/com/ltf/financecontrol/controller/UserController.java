package com.ltf.financecontrol.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;
import com.ltf.financecontrol.dto.UserDto;
import com.ltf.financecontrol.model.User;
import com.ltf.financecontrol.repository.UserRepository;

import com.ltf.financecontrol.services.UserService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(
            UserRepository userRepository,
            UserService userService
    ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(
            @RequestBody @Valid UserDto userDto
    ) {
        User user = this.userService.createUser(userDto);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage("Usuário cadastrado com sucesso!");
        httpResponseDto.setData(user);
        return ResponseEntity.status(200).body(httpResponseDto);
    }

}
