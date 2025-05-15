package com.ltf.financecontrol.modules.auth.service;

import com.ltf.financecontrol.exceptions.InvalidCredentialsException;
import com.ltf.financecontrol.modules.auth.model.dto.CredentialsDto;
import com.ltf.financecontrol.modules.auth.model.dto.CredentialsResponseDto;
import com.ltf.financecontrol.modules.user.model.entities.User;
import com.ltf.financecontrol.modules.user.repository.UserRepository;
import com.ltf.financecontrol.providers.JWTProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthService {

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CredentialsResponseDto authenticated(
            CredentialsDto credentialsDto
    ) {

        User user = this.userRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException());

        boolean passwordMatches = this.passwordEncoder.matches(credentialsDto.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new InvalidCredentialsException();
        }

        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));
        String token = this.jwtProvider.generateTokenToSubject(user.getId().toString(), expiresIn);

        return CredentialsResponseDto
                .builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }

}
