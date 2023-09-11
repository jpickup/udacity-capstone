package com.johnpickup.route.controller;

import com.johnpickup.route.authentication.IAuthenticationFacade;
import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RouteRatingService;
import com.johnpickup.route.data.RouteService;
import com.johnpickup.route.dto.RouteDto;
import com.johnpickup.route.dto.RouteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin
public class RouteController {
    @Autowired
    RouteService routeService;

    @Autowired
    RouteRatingService routeRatingService;

    @Autowired
    RouteMapper routeMapper;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @PostMapping(value="/routes", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteDto saveRoute(@RequestBody RouteDto route) {
        return routeMapper.toDto(routeService.saveRoute(routeMapper.toRoute(route)), routeRatingService.routeRatings(route.getId()));
    }

    @GetMapping(value="/routes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RouteDto> fetchRoutes() {
        List<Route> routes = routeService.listRoutes();
        return routes.stream()
                .filter(route -> ControllerUtils.routeIsVisible(route, authenticationFacade))
                .map(r -> routeMapper.toDto(r, routeRatingService.routeRatings(r.getId())))
                .collect(Collectors.toList());
    }

    @GetMapping(value="/routes/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteDto fetchRoute(@PathVariable("id") String id) {
        return Optional.ofNullable(routeService.getRoute(id))
                .filter(route -> ControllerUtils.routeIsVisible(route, authenticationFacade))
                .map(route -> routeMapper.toDto(route, routeRatingService.routeRatings(id)))
                .orElse(null);
    }

    @PutMapping(value="/routes/{id}", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteDto updateRoute(@RequestBody RouteDto route, @PathVariable("id") String id) {
        return routeMapper.toDto(routeService.updateRoute(routeMapper.toRoute(route), id), routeRatingService.routeRatings(id));
    }

    @DeleteMapping(value="/routes/{id}")
    public String deleteRoute(@PathVariable("id") String id) {
        routeService.deleteRoute(id);
        return "OK";
    }
}
