package com.johnpickup.route.service;

import com.johnpickup.route.data.RouteRating;
import com.johnpickup.route.data.RouteRatingService;
import com.johnpickup.route.repository.RouteRatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class RouteRatingServiceImpl implements RouteRatingService {
    @Autowired
    RouteRatingRepository routeRatingRepository;

    @Override
    public RouteRating saveRouteRating(RouteRating routeRating) {
        log.info("Saving routeRating for route {}", routeRating.getRouteId());
        return routeRatingRepository.save(routeRating);
    }

    @Override
    public RouteRating getRouteRating(String id) {
        log.info("Fetching routeRating {}", id);
        return routeRatingRepository.findById(id).orElse(null);
    }

    @Override
    public List<RouteRating> allRouteRatings() {
        log.info("Listing all routeRatings");
        return StreamSupport.stream(routeRatingRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public RouteRating updateRouteRating(RouteRating routeRating, String id) {
        log.info("Updating routeRating {}", id);
        RouteRating dbRouteRating = routeRatingRepository.findById(id).orElse(null);

        if (dbRouteRating != null) {
            log.info("Updating route rate with id {}", id);
            dbRouteRating.setRouteId(routeRating.getRouteId());
            dbRouteRating.setRating(routeRating.getRating());
            dbRouteRating.setComments(routeRating.getComments());
            return routeRatingRepository.save(dbRouteRating);
        }
        else {
            log.info("Not updating route rate with id {} as it wasn't found", id);
            return null;
        }
    }

    @Override
    public void deleteRouteRating(String id) {
        log.info("Deleting routeRating {}", id);
        routeRatingRepository.deleteById(id);
    }

    @Override
    public List<RouteRating> routeRatings(String routeId) {
        log.info("Finding routeRatings for route {}", routeId);
        return routeRatingRepository.findByRouteId(routeId);
    }
}
