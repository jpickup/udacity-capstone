package com.johnpickup.route.dto;

import com.johnpickup.route.data.RouteRating;
import org.springframework.stereotype.Component;

@Component
public class RouteRatingMapper {
    public RouteRatingDto toDto(RouteRating routeRating) {
        return new RouteRatingDto(routeRating.getId(), routeRating.getRouteId(),
                routeRating.getRating(), routeRating.getComments(),
                routeRating.getUserId());
    }

    public RouteRating toRouteRating(RouteRatingDto routeRatingDto) {
        RouteRating routeRating = RouteRating.builder()
                .id(routeRatingDto.getId())
                .routeId(routeRatingDto.getRouteId())
                .rating(routeRatingDto.getRating())
                .comments(routeRatingDto.getComments())
                .userId(routeRatingDto.getUserId())
                .build();
        return routeRating;
    }
}
