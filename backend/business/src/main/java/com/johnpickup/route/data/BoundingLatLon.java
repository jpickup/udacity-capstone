package com.johnpickup.route.data;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class BoundingLatLon {
    private Double minLatitude;
    private Double minLongitude;
    private Double maxLatitude;
    private Double maxLongitude;
}
