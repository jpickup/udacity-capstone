package com.johnpickup.route.controller;

import com.johnpickup.route.authentication.IAuthenticationFacade;
import com.johnpickup.route.data.RouteService;
import com.johnpickup.route.dto.ElevationDto;
import com.johnpickup.route.elevation.ElevationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
public class ElevationController {
    @Autowired
    RouteService routeService;

    @Autowired
    ElevationMapper elevationMapper;

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @GetMapping(value="/elevation/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ElevationDto fetchElevation(@PathVariable("id") String id) {
        log.info("Fetching elevation for route {}", id);
        return Optional.ofNullable(routeService.getRoute(id))
                .filter(route -> ControllerUtils.routeIsVisible(route, authenticationFacade))
                .map(route -> elevationMapper.toDto(route))
                .orElse(null);
    }


}
