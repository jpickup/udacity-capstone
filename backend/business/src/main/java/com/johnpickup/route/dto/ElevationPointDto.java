package com.johnpickup.route.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ElevationPointDto implements Serializable {
    private final Double distance;
    private final Double elevation;
}
