package com.johnpickup.route.dto;

import com.johnpickup.route.data.LatLon;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LatLonMapper {
    public LatLonDto toDto(LatLon latLon) {
        return Optional.ofNullable(latLon).map(ll -> new LatLonDto(ll.getLatitude(), ll.getLongitude())).orElse(null);
    }
}
