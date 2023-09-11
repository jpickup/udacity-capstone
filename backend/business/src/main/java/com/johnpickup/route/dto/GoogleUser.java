package com.johnpickup.route.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUser {
    private String email;
    private String name;
    private String family_name;
    private String given_name;
    private String picture;
}
