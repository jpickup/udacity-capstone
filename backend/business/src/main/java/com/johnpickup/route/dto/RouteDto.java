package com.johnpickup.route.dto;

import com.johnpickup.route.data.Visibility;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

@Data
@Jacksonized
@Builder
public class RouteDto implements Serializable {
    private final String id;
    private final Visibility visibility;
    private final String name;
    private final String description;
    private final List<RoutePointDto> points;
    private final String owner;
    private final LatLonDto centre;
    private final BoundingLatLonDto bounds;
    private final LatLonDto startPoint;
    private final LatLonDto endPoint;
    private final double length;
    private final Double totalAscent;
    private final Double totalDescent;
    private final List<RouteRatingDto> ratings;
    private final Double averageRating;
}
