package com.johnpickup.route.repository;


import com.johnpickup.route.data.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAllByEmailIgnoreCase(String email);
    List<User> findAllByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
}