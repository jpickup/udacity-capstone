package com.johnpickup.route.controller;

import com.johnpickup.route.authentication.IAuthenticationFacade;
import com.johnpickup.route.convert.RouteToFit;
import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RouteService;
import com.johnpickup.garmin.fit.FitWriter;
import com.johnpickup.garmin.route.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin
public class GarminRouteController {
    @Autowired
    RouteService routeService;

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @GetMapping(value="/fit/{id}", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<byte[]> fetchRoute(@PathVariable("id") String id) throws JAXBException, IOException {
        Route route = routeService.getRoute(id);
        if (ControllerUtils.routeIsVisible(route, authenticationFacade)) {
            RouteToFit routeToFit = new RouteToFit(route);
            Course course = routeToFit.convert();
            FitWriter fitWriter = new FitWriter();
            byte[] bytes = fitWriter.writeFit(course);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + route.getName() + ".fit")
                    .contentLength(bytes.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(bytes);
        } else {
            return null;
        }

    }
}
