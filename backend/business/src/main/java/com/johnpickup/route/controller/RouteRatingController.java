package com.johnpickup.route.controller;

import com.johnpickup.route.data.RouteRating;
import com.johnpickup.route.data.RouteRatingService;
import com.johnpickup.route.dto.RouteRatingDto;
import com.johnpickup.route.dto.RouteRatingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class RouteRatingController {
    @Autowired
    RouteRatingService routeRatingService;

    @Autowired
    RouteRatingMapper routeRatingMapper;

    @PostMapping(value="/ratings", consumes={MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteRatingDto saveRating(@RequestParam String routeId,
                                     @RequestParam int rating,
                                     @RequestParam String comments,
                                     @RequestParam String userId) {
        log.info("Saving rating");
        return routeRatingMapper.toDto(routeRatingService.saveRouteRating(RouteRating.builder()
                        .routeId(routeId)
                        .rating(rating)
                        .comments(comments)
                        .userId(userId)
                .build()));
    }

    @GetMapping(value="/ratings", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RouteRatingDto> fetchRatings() {
        log.info("Getting all ratings");
        return routeRatingService.allRouteRatings().stream()
                .map(r -> routeRatingMapper.toDto(r))
                .toList();
    }

    @GetMapping(value="/ratings/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteRatingDto fetchRating(@PathVariable("id") String id) {
        log.info("Getting rating with id {}", id);
        return routeRatingMapper.toDto(routeRatingService.getRouteRating(id));
    }

    @PutMapping(value="/ratings/{id}", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RouteRatingDto updateRating(@RequestBody RouteRatingDto routeRatingDto, @PathVariable("id") String id) {
        log.info("Updating rating with id {}", id);
        return routeRatingMapper.toDto(routeRatingService.updateRouteRating(routeRatingMapper.toRouteRating(routeRatingDto), id));
    }

    @DeleteMapping(value="/ratings/{id}")
    public String deleteRating(@PathVariable("id") String id) {
        log.info("Deleting rating with id {}", id);
        routeRatingService.deleteRouteRating(id);
        return "OK";
    }
}
