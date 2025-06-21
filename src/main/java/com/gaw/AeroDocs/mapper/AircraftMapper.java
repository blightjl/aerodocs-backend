package com.gaw.AeroDocs.mapper;

import com.gaw.AeroDocs.entity.AircraftModel;
import com.gaw.AeroDocs.dto.AircraftModelDTO;

public class AircraftMapper {
    public static AircraftModelDTO toAircraftDTO(AircraftModel aircraftModel) {
        return new AircraftModelDTO(aircraftModel.getManufacturer(), aircraftModel.getModel(),
                aircraftModel.getVariant(), aircraftModel.getFullModelName());
    }
}