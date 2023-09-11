package com.johnpickup.route.convert;

import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RoutePoint;
import com.johnpickup.route.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class RouteToGpx {
    private final Route route;

    public Gpx convert() {
        if (route == null) return null;

        Gpx gpx = new Gpx();
        gpx.setCreator("udacity");
        gpx.setVersion("1.1");
        gpx.setMetadata(metaData(route));
        gpx.getTrks().add(trackType(route));
        return gpx;
    }

    private TrkType trackType(Route route) {
        TrkType trkType = new TrkType();
        trkType.setName(route.getName());
        trkType.getTrksegs().add(trackSegment(route));
        return trkType;
    }

    private TrksegType trackSegment(Route route) {
        TrksegType trksegType = new TrksegType();
        trksegType.getTrkpts().addAll(trackPoints(route));
        return trksegType;
    }

    private Collection<WptType> trackPoints(Route route) {
        return route.getPoints().stream()
                .map(this::trackSegment)
                .collect(Collectors.toList());
    }

    private WptType trackSegment(RoutePoint routePoint) {
        WptType wptType = new WptType();
        wptType.setLat(BigDecimal.valueOf(routePoint.getLatitude()));
        wptType.setLon(BigDecimal.valueOf(routePoint.getLongitude()));
        wptType.setEle(Optional.ofNullable(routePoint.getElevation()).map(BigDecimal::valueOf).orElse(null));
        return wptType;
    }

    private MetadataType metaData(Route route) {
        MetadataType metadataType = new MetadataType();
        metadataType.setName(route.getName());
        metadataType.setDesc(route.getDescription());
        return metadataType;
    }
}
