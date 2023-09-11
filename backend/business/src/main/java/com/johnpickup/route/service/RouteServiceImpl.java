package com.johnpickup.route.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.johnpickup.route.data.Route;
import com.johnpickup.route.repository.RouteRepository;
import com.johnpickup.route.data.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {
    @Autowired
    DynamoDBMapper dynamoDBMapper;
    @Autowired
    RouteRepository routeRepository;

    @Override
    public Route saveRoute(Route route) {
        log.info("Saving route {}", route.getName());
        return routeRepository.save(route);
    }

    @Override
    public Route getRoute(String id) {
        log.info("Fetching route {}", id);
        return routeRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Route> listRoutes() {
        return StreamSupport.stream(routeRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Route updateRoute(Route route, String id) {
        log.info("Updating route {}", id);
        Route dbRoute = routeRepository.findById(id).orElse(null);

        if (dbRoute != null) {
            dbRoute.setName(route.getName());
            dbRoute.setDescription(route.getDescription());
            if (route.getVisibility() != null) {
                dbRoute.setVisibility(route.getVisibility());
            }
            if (route.getPoints() != null) {
                dbRoute.setPoints(route.getPoints());
            }
            return routeRepository.save(dbRoute);
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteRoute(String id) {
        log.info("Deleting route {}", id);
        routeRepository.deleteById(id);
    }

    @Override
    public List<Route> searchRoutes(String searchText) {
        log.info("Searching routes with name or description like {}", searchText);
        return routeRepository
                .findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchText, searchText);
    }
}
