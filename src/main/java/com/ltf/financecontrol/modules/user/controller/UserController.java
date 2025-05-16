package com.ltf.financecontrol.modules.user.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;

import com.ltf.financecontrol.modules.user.model.dto.UserDto;
import com.ltf.financecontrol.modules.user.model.entities.User;
import com.ltf.financecontrol.modules.user.service.UserService;
import com.ltf.financecontrol.modules.user.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Usuário")
    @Operation(summary = "Cadastro de usuário", description = "Cadastrando um usuário no sistema")
    @ApiResponses(
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                        @Schema(implementation = HttpResponseDto.class)
                    )
            })
    )
    public ResponseEntity<HttpResponseDto> registerUser(
            @RequestBody @Valid UserDto userDto
    ) {

        User userEntity = User
                .builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        User user = this.userService.registerUser(userEntity);
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.addMessage("Usuário cadastrado com sucesso!");
        httpResponseDto.setData(user);
        return ResponseEntity.status(200).body(httpResponseDto);
    }

}
