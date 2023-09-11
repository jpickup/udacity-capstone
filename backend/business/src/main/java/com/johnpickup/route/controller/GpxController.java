package com.johnpickup.route.controller;

import com.johnpickup.route.authentication.IAuthenticationFacade;
import com.johnpickup.route.convert.GpxToRoute;
import com.johnpickup.route.convert.RouteToGpx;
import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RouteService;
import com.johnpickup.route.data.Visibility;
import com.johnpickup.route.domain.Gpx;
import com.johnpickup.route.dto.RouteDto;
import com.johnpickup.route.dto.RouteMapper;
import com.johnpickup.route.elevation.RouteElevationEnricher;
import com.johnpickup.route.gpx.GpxParser;
import com.johnpickup.route.gpx.GpxWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.Collections;

@Slf4j
@RestController
@CrossOrigin
public class GpxController {
    @Autowired
    RouteService routeService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    RouteMapper routeMapper;

    GpxParser gpxParser = new GpxParser();

    @PostMapping(value="/gpx", consumes={MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteDto saveGpx(@RequestBody String gpx) throws JAXBException, FileNotFoundException {
        log.info("Request: {}", gpx);
        String email = authenticationFacade.getAuthenticatedEmail();
        log.info("User email: {}", email);
        Gpx gpxType = gpxParser.readGpxStream(new ByteArrayInputStream(gpx.getBytes()));
        GpxToRoute gpxToRoute = new GpxToRoute(gpxType);
        Route route = gpxToRoute.convert();
        if (email != null && !email.isEmpty()) {
            route.setOwner(email);
        }
        log.info("Route: {}", route);
        Route enrichedRoute = routeElevationEnricher.enrich(route);

        return routeMapper.toDto(routeService.saveRoute(enrichedRoute), Collections.emptyList());
    }

    @Autowired
    RouteElevationEnricher routeElevationEnricher;

    @PostMapping(value="/gpx/upload", consumes={MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteDto uploadGpxFile(@RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam String visibility,
                                  @RequestPart MultipartFile file) throws IOException {
        String email = authenticationFacade.getAuthenticatedEmail();

        log.info("User email: {}", email);
        log.info("Upload GPX: {} ({})", file.getOriginalFilename(), file.getSize());
        if (file.getOriginalFilename()==null || !file.getOriginalFilename().endsWith(".gpx")) {
            throw new RuntimeException("File is not a GPX cannot process upload");
        }
        try {
            Gpx gpxType = gpxParser.readGpxStream(file.getInputStream());
            GpxToRoute gpxToRoute = new GpxToRoute(gpxType);
            Route route = gpxToRoute.convert();
            if (name != null && !name.isEmpty()) {
                log.info("Using explicit route name: {}", name);
                route.setName(name);
            }
            if (description != null && !description.isEmpty()) {
                log.info("Using explicit route description: {}", description);
                route.setDescription(description);
            }
            if (visibility != null && !visibility.isEmpty()) {
                route.setVisibility(Visibility.valueOf(visibility));
            }
            if (email != null && !email.isEmpty()) {
                route.setOwner(email);
            }
            log.info("Route: {}", route);

            Route enrichedRoute = routeElevationEnricher.enrich(route);

            return routeMapper.toDto(routeService.saveRoute(enrichedRoute), Collections.emptyList());
        } catch (JAXBException e) {
            log.error("Error processing GPX file upload", e);
            throw new RuntimeException("File is not a valid GPX");
        }
    }

    @GetMapping(value="/gpx/{id}", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<String> fetchRoute(@PathVariable("id") String id) throws JAXBException {
        Route route = routeService.getRoute(id);
        if (ControllerUtils.routeIsVisible(route, authenticationFacade)) {
            RouteToGpx routeToGpx = new RouteToGpx(route);
            Gpx gpx = routeToGpx.convert();
            GpxWriter gpxWriter = new GpxWriter();
            String gpxString = gpxWriter.writeGpx(gpx);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + route.getName() + ".gpx")
                    .contentLength(gpxString.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(gpxString);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
