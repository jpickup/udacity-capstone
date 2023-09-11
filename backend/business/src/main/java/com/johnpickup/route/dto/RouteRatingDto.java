package com.johnpickup.route.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteRatingDto implements Serializable {
    private final String id;
    private final String routeId;
    private final int rating;
    private final String comments;
    private final String userId;
}
