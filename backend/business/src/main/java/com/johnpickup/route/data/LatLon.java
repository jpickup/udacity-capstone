package com.johnpickup.route.data;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class LatLon {
    private Double latitude;
    private Double longitude;

    public static class LatLonConverter implements DynamoDBTypeConverter<String, LatLon> {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convert(LatLon latLon) {
            try {
                return objectMapper.writeValueAsString(latLon);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public LatLon unconvert(String s) {
            try {
                return objectMapper.readValue(s, LatLon.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
