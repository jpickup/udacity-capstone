package com.johnpickup.route.dto;

import com.johnpickup.route.data.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return Optional.ofNullable(user)
                .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getRole()))
                .orElse(null);
    }

    public User toUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();
    }
}
