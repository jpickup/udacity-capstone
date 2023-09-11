package com.johnpickup.route.controller;

import com.johnpickup.route.data.User;
import com.johnpickup.route.data.UserRole;
import com.johnpickup.route.data.UserService;
import com.johnpickup.route.dto.UserCredentials;
import com.johnpickup.route.dto.UserDto;
import com.johnpickup.route.dto.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    UserService userService;
    
    @Autowired
    UserMapper userMapper;

    @PostMapping(value="/login", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto loginUser(@RequestBody UserCredentials userCredentials) {
        log.info("Login {}", userCredentials);
        User user = userService.findUser(userCredentials.getEmail());
        if (user == null) {
            user = userService.saveUser(User.builder()
                    .role(UserRole.READ_ONLY)
                    .name(userCredentials.getUser().getName())
                    .email(userCredentials.getUser().getEmail())
                    .build());
            log.info("Created new user {}", user);
        }
        return userMapper.toDto(user);
    }
}
