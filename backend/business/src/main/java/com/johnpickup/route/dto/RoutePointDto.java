package com.johnpickup.route.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoutePointDto implements Serializable {
    private final String name;
    private final String description;
    private final Double latitude;
    private final Double longitude;
    private final Double elevation;
}
