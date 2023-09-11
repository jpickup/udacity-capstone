package com.johnpickup.route.controller;

import com.johnpickup.route.data.User;
import com.johnpickup.route.data.UserService;
import com.johnpickup.route.dto.UserDto;
import com.johnpickup.route.dto.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    
    @Autowired
    UserMapper userMapper;

    @PostMapping(value="/users", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto saveUser(@RequestBody UserDto user) {
        return userMapper.toDto(userService.saveUser(userMapper.toUser(user)));
    }

    @GetMapping(value="/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserDto> fetchUsers() {
        log.info("Getting users");
        List<User> users = userService.listUsers();
        return users.stream().map(r -> userMapper.toDto(r)).collect(Collectors.toList());
    }

    @GetMapping(value="/users/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto fetchUser(@PathVariable("id") String id) {
        log.info("Getting user {}", id);
        return userMapper.toDto(userService.getUser(id));
    }

    @PostMapping(value="/users/{id}", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable("id") String id) {
        log.info("Updating user {} with {}", id, user);
        return userMapper.toDto(userService.updateUser(userMapper.toUser(user), id));
    }

    @DeleteMapping(value="/users/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        log.info("Deleting user {}", id);
        userService.deleteUser(id);
        return "OK";
    }

    @PostMapping(value="/users/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserDto> searchUsers(
            @RequestParam String searchText) {
        log.info("Searching for users with {}", searchText);
        List<User> routes = userService.searchUsers(searchText);
        return routes.stream().map(r -> userMapper.toDto(r)).collect(Collectors.toList());
    }
}
