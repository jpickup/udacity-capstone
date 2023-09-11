package com.johnpickup.route.data;

import java.util.List;

public interface RouteService {
    Route getRoute(String id);
    List<Route> listRoutes();

    Route saveRoute(Route route);
    Route updateRoute(Route route, String id);
    void deleteRoute(String id);

    List<Route> searchRoutes(String searchText);
}
