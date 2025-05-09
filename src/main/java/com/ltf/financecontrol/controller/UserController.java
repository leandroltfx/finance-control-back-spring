package com.ltf.financecontrol.controller;

import com.ltf.financecontrol.model.User;
import com.ltf.financecontrol.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String registerUser(
            @RequestBody User user
    ) {
        var id = UUID.randomUUID().toString();
        user.setId(id);

        this.userRepository.save(user);
        return "";
    }

}
