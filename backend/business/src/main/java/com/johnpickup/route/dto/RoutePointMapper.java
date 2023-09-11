package com.johnpickup.route.dto;

import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RoutePoint;
import org.springframework.stereotype.Component;

@Component
public class RoutePointMapper {
    public RoutePointDto toDto(RoutePoint routePoint) {
        return new RoutePointDto(routePoint.getName(), routePoint.getDescription(),
                routePoint.getLatitude(), routePoint.getLongitude(), routePoint.getElevation());
    }

    public RoutePoint toRoutePoint(RoutePointDto routePointDto, Route route) {
        RoutePoint routePoint = RoutePoint.builder()
                .name(routePointDto.getName())
                .description(routePointDto.getDescription())
                .latitude(routePointDto.getLatitude())
                .longitude(routePointDto.getLongitude())
                .elevation(routePointDto.getElevation())
                .build();
        return routePoint;
    }
}
