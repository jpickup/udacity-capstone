package com.johnpickup.route.repository;


import com.johnpickup.route.data.Route;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface RouteRepository extends CrudRepository<Route, String> {
    List<Route> findByName(String name);

    List<Route> findByNameLike(String name);
    List<Route> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}