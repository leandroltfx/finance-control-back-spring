package com.ltf.financecontrol.modules.user.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;

import com.ltf.financecontrol.modules.user.model.dto.UserDto;
import com.ltf.financecontrol.modules.user.model.entities.User;
import com.ltf.financecontrol.modules.user.service.UserService;
import com.ltf.financecontrol.modules.user.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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
