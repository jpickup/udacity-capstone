package com.johnpickup.route.data;

import java.util.List;

public interface UserService {
    User getUser(String id);
    List<User> listUsers();

    User saveUser(User user);
    User updateUser(User user, String id);
    void deleteUser(String id);

    User findUser(String email);

    List<User> searchUsers(String searchText);
}
