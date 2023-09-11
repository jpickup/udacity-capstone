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
public class RouteMetaController {
    @Autowired
    RouteService routeService;

    @Autowired
    RouteRatingService routeRatingService;

    @Autowired
    RouteMapper routeMapper;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @GetMapping(value="/routemeta", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RouteDto> fetchRoutes() {
        log.info("fetching routes");
        List<Route> routes = routeService.listRoutes();
        return routes.stream()
                .filter(route -> ControllerUtils.routeIsVisible(route, authenticationFacade))
                .map(r -> routeMapper.toMetaDto(r, routeRatingService.routeRatings(r.getId())))
                .collect(Collectors.toList());
    }

    @GetMapping(value="/routemeta/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteDto fetchRoute(@PathVariable("id") String id) {
        return Optional.ofNullable(routeService.getRoute(id))
                .filter(route -> ControllerUtils.routeIsVisible(route, authenticationFacade))
                .map(route -> routeMapper.toMetaDto(route, routeRatingService.routeRatings(id)))
                .orElse(null);
    }

    @PutMapping(value="/routemeta/{id}", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteDto updateRoute(@RequestBody RouteDto route, @PathVariable("id") String id) {
        log.info("updating route {} with {}", id, route);
        return routeMapper.toDto(routeService.updateRoute(routeMapper.toRoute(route), id), routeRatingService.routeRatings(id));
    }

    @DeleteMapping(value="/routemeta/{id}")
    public String deleteRoute(@PathVariable("id") String id) {
        log.info("deleting route {}", id);
        routeService.deleteRoute(id);
        return "OK";
    }
}
