package com.johnpickup.route.data;

import java.util.List;

public interface RouteRatingService {
    RouteRating getRouteRating(String id);
    List<RouteRating> allRouteRatings();

    List<RouteRating> routeRatings(String routeId);

    RouteRating saveRouteRating(RouteRating routeRating);
    RouteRating updateRouteRating(RouteRating routeRating, String id);
    void deleteRouteRating(String id);
}
