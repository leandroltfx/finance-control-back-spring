package com.ltf.financecontrol.modules.user.service;

import com.ltf.financecontrol.exceptions.UserFoundException;
import com.ltf.financecontrol.modules.user.model.entities.User;
import com.ltf.financecontrol.modules.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(
            User userEntity
    ) {

        this.userRepository
                .findByEmail(userEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException("Este e-mail já está sendo utilizado.");
                });

        this.userRepository
                .findByUsername(userEntity.getUsername())
                .ifPresent(user -> {
                    throw new UserFoundException("Este nome de usuário já está sendo utilizado.");
                });

        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        return this.userRepository.save(userEntity);
    }

}
