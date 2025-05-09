package com.ltf.financecontrol.controller;

import com.ltf.financecontrol.dto.LoginDto;
import com.ltf.financecontrol.dto.LoggedUserDto;
import com.ltf.financecontrol.model.User;
import com.ltf.financecontrol.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public LoggedUserDto login(
            @RequestBody LoginDto loginDto
    ) {
        LoggedUserDto loggedUserDto = new LoggedUserDto();
        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equals(loginDto.getEmail()) && user.getPassword().equals(loginDto.getPassword())) {
                loggedUserDto.setId(user.getId());
                loggedUserDto.setUsername(user.getUsername());
                loggedUserDto.setEmail(user.getEmail());
            }
        }
        return loggedUserDto;
    }

}
