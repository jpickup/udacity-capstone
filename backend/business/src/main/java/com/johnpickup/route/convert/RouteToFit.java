package com.johnpickup.route.convert;

import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.RoutePoint;
import com.johnpickup.garmin.route.Course;
import com.johnpickup.garmin.route.CoursePoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class RouteToFit {
    private final Route route;
    public Course convert() {
        if (route == null) return null;

        return new Course(route.getName(), points());
    }

    private List<CoursePoint> points() {
        return route.getPoints().stream()
                .map(this::coursePoint)
                .collect(Collectors.toList());
    }

    private CoursePoint coursePoint(RoutePoint routePoint) {
        return new CoursePoint(
                Math.toRadians(routePoint.getLatitude()),
                Math.toRadians(routePoint.getLongitude()),
                routePoint.getElevation()==null?0d:routePoint.getElevation());
    }
}
