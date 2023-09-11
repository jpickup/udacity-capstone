package com.johnpickup.route.configuration;

import com.johnpickup.route.elevation.ElevationMapper;
import com.johnpickup.route.elevation.RouteElevationEnricher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class ElevationConfiguration {

    @Value("${google.elevation.api.url}")
    private String url;
    @Value("${google.elevation.api.key}")
    private String key;

    @Bean
    RouteElevationEnricher routeElevationEnricher() {
        return new RouteElevationEnricher(url, key, restTemplate());
    }

    @Bean
    ElevationMapper elevationMapper() {
        return new ElevationMapper();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
