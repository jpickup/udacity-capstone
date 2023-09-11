package com.johnpickup.route.dto;

import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RouteRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RouteMapper {
    @Autowired
    RoutePointMapper routePointMapper;

    @Autowired
    LatLonMapper latLonMapper;

    @Autowired
    BoundingLatLonMapper boundingLatLonMapper;

    @Autowired
    RouteRatingMapper routeRatingMapper;

    public RouteDto toDto(Route route, List<RouteRating> ratings) {
        return Optional.ofNullable(route)
                .map(r -> new RouteDto(r.getId(),
                        r.getVisibility(),
                        r.getName(),
                        r.getDescription(),
                        r.getPoints().stream().map(p -> routePointMapper.toDto(p)).collect(Collectors.toList()),
                        r.getOwner(),
                        latLonMapper.toDto(r.centre()),
                        boundingLatLonMapper.toDto(r.bounds()),
                        latLonMapper.toDto(r.startPoint()),
                        latLonMapper.toDto(r.endPoint()),
                        r.length(),
                        r.totalAscent(),
                        r.totalDescent(),
                        ratings.stream().map(rr -> routeRatingMapper.toDto(rr)).collect(Collectors.toList()),
                        averageRating(ratings)))
                .orElse(null);
    }

    public RouteDto toMetaDto(Route route, List<RouteRating> ratings) {
        return Optional.ofNullable(route)
                .map(r -> new RouteDto(r.getId(),
                        r.getVisibility(),
                        r.getName(),
                        r.getDescription(),
                        null,
                        r.getOwner(),
                        latLonMapper.toDto(r.centre()),
                        boundingLatLonMapper.toDto(r.bounds()),
                        latLonMapper.toDto(r.startPoint()),
                        latLonMapper.toDto(r.endPoint()),
                        r.length(),
                        r.totalAscent(),
                        r.totalDescent(),
                        ratings.stream().map(rr -> routeRatingMapper.toDto(rr)).collect(Collectors.toList()),
                        averageRating(ratings)))
                .orElse(null);
    }

    private Double averageRating(List<RouteRating> ratings) {
        int ratingCount = ratings.size();
        if (ratingCount == 0) {
            return null;
        } else {
            return ratings.stream().map(RouteRating::getRating).reduce(0, Integer::sum) * 1.0d / ratingCount;
        }
    }

    public Route toRoute(RouteDto routeDto) {
        Route route = Route.builder()
                .id(routeDto.getId())
                .name(routeDto.getName())
                .description(routeDto.getDescription())
                .owner(routeDto.getOwner())
                .visibility(routeDto.getVisibility())
                .build();

        if (routeDto.getPoints() != null) {
            route.setPoints(routeDto.getPoints().stream()
                    .map(p -> routePointMapper.toRoutePoint(p, route))
                    .collect(Collectors.toList()));
        }

        return route;
    }
}
