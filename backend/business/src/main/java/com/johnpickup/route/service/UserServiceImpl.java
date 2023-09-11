package com.johnpickup.route.service;

import com.johnpickup.route.data.User;
import com.johnpickup.route.data.UserService;
import com.johnpickup.route.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String id) {
        log.info("get user {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> listUsers() {
        log.info("list users");
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public User saveUser(User user) {
        log.info("save user {}", user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, String id) {
        log.info("update user id {} with {}", id, user);
        userRepository.findById(id).ifPresent(u -> {
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setRole(user.getRole());
            userRepository.save(u);
        });

        return getUser(id);
    }

    @Override
    public void deleteUser(String id) {
        log.info("delete user {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public User findUser(String email) {
        log.info("find user {}", email);
        return userRepository.findAllByEmailIgnoreCase(email).stream().findFirst().orElse(null);
    }

    @Override
    public List<User> searchUsers(String searchText) {
        log.info("search user {}", searchText);
        return userRepository.findAllByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(searchText, searchText);
    }
}
