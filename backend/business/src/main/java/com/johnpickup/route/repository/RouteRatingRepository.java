package com.johnpickup.route.repository;

import com.johnpickup.route.data.RouteRating;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface RouteRatingRepository extends CrudRepository<RouteRating, String> {
    List<RouteRating> findByRouteId(String routeId);
}