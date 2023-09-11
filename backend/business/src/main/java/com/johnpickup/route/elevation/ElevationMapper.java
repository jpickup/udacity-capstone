package com.johnpickup.route.elevation;

import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RoutePoint;
import com.johnpickup.route.dto.ElevationDto;
import com.johnpickup.route.dto.ElevationPointDto;
import com.johnpickup.route.util.Haversine;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ElevationMapper {
    public ElevationDto toDto(Route route) {
        if (route == null) return null;

        List<ElevationPointDto> elevationPoints = new ArrayList<>();
        double distance = 0d;
        RoutePoint previous = null;
        for (RoutePoint point : route.getPoints()) {
            if (previous != null) {
                distance += Haversine.distanceDegrees(previous.getLatitude(), previous.getLongitude(), point.getLatitude(), point.getLongitude());
            }

            if (point.getElevation() != null) {
                ElevationPointDto elevationPoint = new ElevationPointDto(distance, point.getElevation());
                elevationPoints.add(elevationPoint);
            }
            previous = point;
        }
        if (elevationPoints.isEmpty()) {
            log.info("No elevation data available for route {}", route.getId());
            return null;
        }
        log.info("Extracted {} elevation points for route {}", elevationPoints.size(), route.getId());
        return new ElevationDto(route.getId(), elevationPoints);
    }
}
