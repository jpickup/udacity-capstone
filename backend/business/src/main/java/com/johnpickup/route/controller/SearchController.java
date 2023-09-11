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
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin
public class SearchController {
    @Autowired
    RouteService routeService;

    @Autowired
    RouteRatingService routeRatingService;

    @Autowired
    RouteMapper routeMapper;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @PostMapping(value="/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RouteDto> searchRoutes(
            @RequestParam String searchText) {
        log.info("Searching for routes with key: {}", searchText);
        List<Route> routes = routeService.searchRoutes(searchText);
        log.info("Found {} matching routes", routes.size());

        return routes.stream()
                .filter(route -> ControllerUtils.routeIsVisible(route, authenticationFacade))
                .map(r -> routeMapper.toMetaDto(r, routeRatingService.routeRatings(r.getId())))
                .collect(Collectors.toList());
    }

}
