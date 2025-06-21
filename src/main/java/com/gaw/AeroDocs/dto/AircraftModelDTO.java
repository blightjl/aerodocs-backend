package com.gaw.AeroDocs.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AircraftModelDTO {
    private String manufacturer;
    private String model;
    private String variant;
    private String fullModelName;
}
