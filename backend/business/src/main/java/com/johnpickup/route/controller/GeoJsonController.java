package com.johnpickup.route.controller;

import com.johnpickup.route.authentication.IAuthenticationFacade;
import com.johnpickup.route.convert.RouteToGeoJson;
import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.geojson.GeoJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class GeoJsonController {
    @Autowired
    RouteService routeService;

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @GetMapping(value="/geojson/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public GeoJsonObject fetchRoute(@PathVariable("id") String id) {
        Route route = routeService.getRoute(id);
        if (ControllerUtils.routeIsVisible(route, authenticationFacade)) {
            RouteToGeoJson routeToGeoJson = new RouteToGeoJson(route);
            GeoJsonObject geoJson = routeToGeoJson.convert();
            return geoJson;
        } else {
            return null;
        }
    }
}
