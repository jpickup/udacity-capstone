package com.johnpickup.route.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.johnpickup.route.data.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
    private final String id;

    private final String name;

    private final String email;

    private final UserRole role;
}
