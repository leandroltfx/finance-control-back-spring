package com.ltf.financecontrol.services;

import com.ltf.financecontrol.dto.UserDto;
import com.ltf.financecontrol.model.User;
import com.ltf.financecontrol.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public User createUser(
        UserDto userDto
    ) {
        User newUser = new User();
        newUser.setId(UUID.randomUUID().toString());
        // Transformar em LoggedUserDto e devolver
        return this.userRepository.save(newUser);
    }

}
