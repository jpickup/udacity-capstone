package com.johnpickup.route.dto;

import com.johnpickup.route.data.BoundingLatLon;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BoundingLatLonMapper {
    public BoundingLatLonDto toDto(BoundingLatLon latLon) {
        return Optional.ofNullable(latLon)
                .map(bll -> new BoundingLatLonDto(bll.getMinLatitude(), bll.getMinLongitude(),
                        bll.getMaxLatitude(), bll.getMaxLongitude()))
                .orElse(null);
    }
}
