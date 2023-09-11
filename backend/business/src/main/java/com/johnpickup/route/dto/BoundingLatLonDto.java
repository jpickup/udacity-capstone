package com.johnpickup.route.dto;

import lombok.Data;

@Data
public class BoundingLatLonDto {
    private final Double minLatitude;
    private final Double minLongitude;
    private final Double maxLatitude;
    private final Double maxLongitude;
}
