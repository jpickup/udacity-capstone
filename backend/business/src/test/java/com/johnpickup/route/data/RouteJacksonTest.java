package com.johnpickup.route.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test Jackson serialisation
 */
class RouteJacksonTest {
    private ObjectMapper mapper;
    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
    }
    @Test
    public void serialisesComputedFields() throws JsonProcessingException {
        Route route = Route.builder().build();
        route.setId("routeId");
        route.setName("routeName");
        route.setDescription("route description");
        route.setVisibility(Visibility.PUBLIC);
        route.setPoints(Arrays.asList(
                RoutePoint.builder()
                        .latitude(1d)
                        .longitude(1d)
                        .build(),
                RoutePoint.builder()
                        .latitude(2d)
                        .longitude(2d)
                        .build()));

        String json = mapper.writeValueAsString(route);

        assertThat(json, is("{\"id\":\"routeId\",\"visibility\":\"PUBLIC\",\"name\":\"routeName\",\"description\":\"route description\",\"points\":[{\"name\":null,\"description\":null,\"latitude\":1.0,\"longitude\":1.0,\"elevation\":null},{\"name\":null,\"description\":null,\"latitude\":2.0,\"longitude\":2.0,\"elevation\":null}],\"owner\":null,\"length\":157225.4320380729,\"bounds\":{\"minLatitude\":0.9,\"minLongitude\":0.9,\"maxLatitude\":2.1,\"maxLongitude\":2.1},\"centre\":{\"latitude\":1.5,\"longitude\":1.5},\"startPoint\":{\"latitude\":1.0,\"longitude\":1.0},\"endPoint\":{\"latitude\":2.0,\"longitude\":2.0},\"totalAscent\":null,\"totalDescent\":null}"));
    }

    @Test
    public void deserialiseIgnoresComputedFields() throws JsonProcessingException {
        String json = "{\"id\":\"routeId\",\"visibility\":\"PUBLIC\",\"name\":\"routeName\",\"description\":\"route description\",\"points\":[{\"name\":null,\"description\":null,\"latitude\":1.0,\"longitude\":1.0,\"elevation\":null},{\"name\":null,\"description\":null,\"latitude\":2.0,\"longitude\":2.0,\"elevation\":null}],\"length\":157225.4320380729,\"bounds\":{\"minLatitude\":0.9,\"minLongitude\":0.9,\"maxLatitude\":2.1,\"maxLongitude\":2.1},\"centre\":{\"latitude\":1.5,\"longitude\":1.5},\"startPoint\":{\"latitude\":1.0,\"longitude\":1.0},\"endPoint\":{\"latitude\":2.0,\"longitude\":2.0},\"totalAscent\":null,\"totalDescent\":null}";

        Route route = mapper.readValue(json, Route.class);

        assertThat(route.getId(), is("routeId"));
    }
}