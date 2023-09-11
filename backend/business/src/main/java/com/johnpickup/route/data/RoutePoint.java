package com.johnpickup.route.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.Arrays;
import java.util.List;

@Builder
@Data
@Jacksonized
public class RoutePoint {
    private String name;

    private String description;

    private Double latitude;

    private Double longitude;

    private Double elevation;

    public static class RoutePointsConverter implements DynamoDBTypeConverter<String, List<RoutePoint>> {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convert(List<RoutePoint> routePoint) {
            try {
                return objectMapper.writeValueAsString(routePoint);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<RoutePoint> unconvert(String s) {
            try {
                return Arrays.asList(objectMapper.readValue(s, RoutePoint[].class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static class RoutePointConverter implements DynamoDBTypeConverter<String, RoutePoint> {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convert(RoutePoint routePoint) {
            try {
                return objectMapper.writeValueAsString(routePoint);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public RoutePoint unconvert(String s) {
            try {
                return objectMapper.readValue(s, RoutePoint.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}