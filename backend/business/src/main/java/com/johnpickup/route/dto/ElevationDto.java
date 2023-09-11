package com.johnpickup.route.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class ElevationDto  implements Serializable {
    private final String routeId;
    private final List<ElevationPointDto> points;
}
