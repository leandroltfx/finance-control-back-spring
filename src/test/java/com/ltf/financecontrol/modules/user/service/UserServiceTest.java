package com.ltf.financecontrol.modules.user.service;

import com.ltf.financecontrol.exceptions.UserFoundException;
import com.ltf.financecontrol.modules.user.model.entities.User;
import com.ltf.financecontrol.modules.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("deve registrar um usuário no banco de dados e retornar a entidade registrada")
    public void registerUser() {

        User userEntity = User
                .builder()
                .username("user")
                .email("email@email.com")
                .password("pass123")
                .build();

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any(String.class))).thenReturn("passencoded");
        when(userRepository.save(any(User.class))).thenReturn(userEntity);

        var result = userService.registerUser(userEntity);

        assertNotNull(result);
        assertInstanceOf(User.class, result);
        assertEquals("user", result.getUsername());
        assertEquals("email@email.com", result.getEmail());
        assertEquals("passencoded", result.getPassword());
    }

    @Test
    @DisplayName("Deve disparar expection de usuário existente em caso de tentativa de registro de email já cadastrado")
    public void registerUserWithErrorExistingEmail() {

        User userEntity = User
                .builder()
                .username("user")
                .email("email@email.com")
                .password("pass123")
                .build();

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));

        try {
            userService.registerUser(userEntity);
        } catch (Exception exception) {
            assertInstanceOf(UserFoundException.class, exception);
        }
    }

    @Test
    @DisplayName("Deve disparar expection de usuário existente em caso de tentativa de registro de nome de usuário já cadastrado")
    public void registerUserWithErrorExistingUsername() {

        User userEntity = User
                .builder()
                .username("user")
                .email("email@email.com")
                .password("pass123")
                .build();

        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(userEntity));

        try {
            userService.registerUser(userEntity);
        } catch (Exception exception) {
            assertInstanceOf(UserFoundException.class, exception);
        }
    }
}
