package com.johnpickup.route.convert;

import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RoutePoint;
import com.johnpickup.route.data.Visibility;
import com.johnpickup.route.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class GpxToRoute {
    private final Gpx gpx;
    private final AtomicLong idCounter = new AtomicLong();

    public Route convert() {
        Route route = Route
                .builder()
                .name(gpx.getMetadata().getName())
                .description(gpx.getMetadata().getDesc())
                .visibility(Visibility.PUBLIC)
                .build();
        route.setPoints(getPoints(route));
        return route;
    }

    private List<RoutePoint> getPoints(Route route) {
        if (gpx.getTrks().size() > 0) {
            return getTrackPoints(gpx.getTrks().get(0), route);
        }
        if (gpx.getRtes().size() > 0) {
            return getRoutePoints(gpx.getRtes().get(0), route);
        }

        throw new RuntimeException("GPX doesn't contain a route or a track, nothing to convert");
    }

    private List<RoutePoint> getRoutePoints(RteType rteType, Route route) {
        return rteType.getRtepts().stream()
                .map(pt -> RoutePoint.builder()
                        //.id(idCounter.getAndIncrement())
                        .name(pt.getName())
                        .description(pt.getDesc())
                        .latitude(pt.getLat().doubleValue())
                        .longitude(pt.getLon().doubleValue())
                        .elevation(Optional.ofNullable(pt.getEle()).map(BigDecimal::doubleValue).orElse(null))
                        .build())
                .collect(Collectors.toList());
    }

    private List<RoutePoint> getTrackPoints(TrkType trkType, Route route) {
        return trkType.getTrksegs().stream().flatMap(ts -> ts.getTrkpts().stream())
                .map(pt -> RoutePoint.builder()
                        .name(pt.getName())
                        .description(pt.getDesc())
                        .latitude(pt.getLat().doubleValue())
                        .longitude(pt.getLon().doubleValue())
                        .elevation(Optional.ofNullable(pt.getEle()).map(BigDecimal::doubleValue).orElse(null))
                        .build())
                .collect(Collectors.toList());
    }
}
