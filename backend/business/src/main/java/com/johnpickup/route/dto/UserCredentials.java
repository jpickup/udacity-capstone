package com.johnpickup.route.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * JavaScript object used by the vue ui
 */
@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCredentials {
    private final GoogleUser user;
    private final String credential;
    private final String email;
    private final String role;

}
