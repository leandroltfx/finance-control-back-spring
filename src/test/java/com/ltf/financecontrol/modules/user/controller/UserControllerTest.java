package com.ltf.financecontrol.modules.user.controller;

import com.ltf.financecontrol.dto.HttpResponseDto;
import com.ltf.financecontrol.modules.user.model.dto.UserDto;
import com.ltf.financecontrol.modules.user.model.entities.User;
import com.ltf.financecontrol.modules.user.service.UserService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    @DisplayName("Deve devolver uma ResponseEntity com HttpResponseDto")
    public void registerUser() {
        // Arranje
        User userEntity = User.builder()
                .id(UUID.randomUUID())
                .username("user")
                .email("email@email.com")
                .password("senha123")
                .build();
        UserDto userDto = UserDto
                .builder()
                .username("user")
                .email("email@email.com")
                .password("senha123")
                .build();

        when(userService.registerUser(any(User.class))).thenReturn(userEntity);

        ResponseEntity<HttpResponseDto> response = userController.registerUser(userDto);

        assertInstanceOf(ResponseEntity.class, response);
        assertInstanceOf(HttpResponseDto.class, response.getBody());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userEntity, response.getBody().getData());
        assertEquals("Usuário cadastrado com sucesso!", response.getBody().getListMessage().getFirst());
    }

}
