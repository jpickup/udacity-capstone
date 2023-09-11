package com.johnpickup.route.elevation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RoutePoint;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class RouteElevationEnricher {
    private final String googleElevationApiUrl;
    private final String googleApiKey;
    private final RestTemplate restTemplate;
    private static final int MAX_BATCH_SIZE = 400;

    public Route enrich(Route route) {
        try {
            if (isMissingElevation(route)) {
                log.info("Enriching route {}({}) with elevation data", route.getId(), route.getName());

                // limit of 8k in the URL, with 20 bytes per lat/lon gives ~400 max in a call - need to batch the requests

                List<List<RoutePoint>> pointBatches = Lists.partition(route.getPoints(), MAX_BATCH_SIZE);

                List<RoutePoint> enrichedPoints = pointBatches.stream().map(this::convertBatch)
                        .flatMap(List::stream)
                        .collect(Collectors.toList());


                Route enrichedRoute = Route.builder()
                        .id(route.getId())
                        .name(route.getName())
                        .description(route.getDescription())
                        .visibility(route.getVisibility())
                        .owner(route.getOwner())
                        .points(enrichedPoints)
                        .build();
                log.info("Enriched route: {}", enrichedRoute);
                return enrichedRoute;
            }
        } catch (Exception ex) {
            log.warn("Failed to enrich with elevation data", ex);
        }

        return route;
    }

    private List<RoutePoint> convertBatch(List<RoutePoint> routePoints) {
        String latLngs = routePoints.stream().map(p -> String.format("%f,%f", p.getLatitude(), p.getLongitude())).collect(Collectors.joining("|"));
        String fullUrl = googleElevationApiUrl + "?key=" + googleApiKey + "&locations=" + latLngs;
        log.debug("Request URL {}", fullUrl);

        ResponseEntity<ElevationResponse> elevationResponse = restTemplate.getForEntity(fullUrl, ElevationResponse.class);
        if (elevationResponse.getStatusCode().is2xxSuccessful() && elevationResponse.getBody() != null && elevationResponse.getBody().getStatus().equals("OK")) {
            List<RoutePoint> enrichedPoints = elevationResponse.getBody().results.stream()
                    .map(ee -> RoutePoint.builder()
                            .latitude(ee.location.lat)
                            .longitude(ee.location.lng)
                            .elevation(ee.elevation)
                            .build())
                    .collect(Collectors.toList());
            log.info("Converted {} points to {} enriched points", routePoints.size(), enrichedPoints.size());
            return enrichedPoints;
        }
        log.info("Failed to convert {} points; using originals", routePoints.size());
        return routePoints;
    }

    private boolean isMissingElevation(Route route) {
        return route.getPoints().stream().noneMatch(p -> p.getElevation() != null);
    }

    /*
    Example response JSON:
        {
           "results" : [
              {
                 "elevation" : 140.8794097900391,
                 "location" : {
                    "lat" : 51.270574,
                    "lng" : -1.273484
                 },
                 "resolution" : 19.08790397644043
              }
           ],
           "status" : "OK"
        }
    */
    @Data
    @Jacksonized
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ElevationResponse {
        String status;
        List<ElevationEntry> results;
    }

    @Data
    @Jacksonized
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ElevationEntry {
        double elevation;
        double resolution;
        ElevationLocation location;
    }

    @Data
    @Jacksonized
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ElevationLocation {
        double lat;
        double lng;
    }
}
