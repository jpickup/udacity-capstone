package com.johnpickup.route.convert;

import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RoutePoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geojson.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class RouteToGeoJson {
    private final Route route;
    public GeoJsonObject convert() {
        FeatureCollection featureCollection = new FeatureCollection();

        featureCollection.add(lineStringFeature());
        featureCollection.add(startFeature());
        featureCollection.add(endFeature());
        featureCollection.addAll(pointFeatures());

        return featureCollection;
    }

    private Feature startFeature() {
        RoutePoint startPoint = RoutePoint.builder()
                .name("Start")
                .latitude(route.startPoint().getLatitude())
                .longitude(route.startPoint().getLongitude())
                .build();
        return pointFeature(startPoint, Collections.singletonMap("marker-color", "Green"));
    }

    private Feature endFeature() {
        RoutePoint endPoint = RoutePoint.builder()
                .name("End")
                .latitude(route.endPoint().getLatitude())
                .longitude(route.endPoint().getLongitude())
                .build();
        return pointFeature(endPoint, Collections.singletonMap("marker-color", "Red"));
    }

    private Collection<Feature> pointFeatures() {
        return route.getPoints().stream()
                .filter(p -> p.getName() != null && !p.getName().isEmpty())
                .map(this::pointFeature)
                .collect(Collectors.toList());
    }

    private Feature lineStringFeature() {
        Feature feature = new Feature();
        feature.setGeometry(lineStringGeometry());
        return feature;
    }

    private GeoJsonObject lineStringGeometry() {
        LineString lineString = new LineString();
        lineString.setCoordinates(route.getPoints().stream().map(this::toLngLatAlt).collect(Collectors.toList()));
        return lineString;
    }

    private Feature pointFeature(RoutePoint routePoint) {
        return pointFeature(routePoint, Collections.emptyMap());
    }

    private Feature pointFeature(RoutePoint routePoint, Map<String, String> additionalProperties) {
        Feature feature = new Feature();
        feature.setGeometry(pointGeometry(routePoint));
        feature.setProperty("name", routePoint.getName());
        if (routePoint.getDescription() != null) {
            feature.setProperty("description", routePoint.getDescription());
        }
        additionalProperties.forEach(feature::setProperty);
        return feature;
    }

    private GeoJsonObject pointGeometry(RoutePoint routePoint) {
        Point point = new Point();
        point.setCoordinates(toLngLatAlt(routePoint));
        return point;
    }

    private LngLatAlt toLngLatAlt(RoutePoint p) {
        if (p.getElevation() != null) {
            return new LngLatAlt(p.getLongitude(), p.getLatitude(), p.getElevation());
        } else {
            return new LngLatAlt(p.getLongitude(), p.getLatitude());
        }
    }
}
