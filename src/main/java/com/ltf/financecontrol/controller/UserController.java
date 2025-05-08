package com.ltf.financecontrol.controller;

import com.ltf.financecontrol.model.User;
import com.ltf.financecontrol.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public String registerUser(
            @RequestBody User user
    ) {
        System.out.println(user);

        var id = UUID.randomUUID().toString();
        user.setId(id);

        this.userRepository.save(user);
        return "";
    }

}
