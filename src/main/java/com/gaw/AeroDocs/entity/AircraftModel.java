package com.gaw.AeroDocs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;
import java.io.Serializable;

@Entity
@Table(name = "aircraft_models")
@IdClass(AircraftModelId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AircraftModel {
    @Id
    @NotBlank(message = "Manufacturer is required")
    @Column(length = 100)
    private String manufacturer;

    @Id
    @NotBlank(message = "Model is required")
    @Column(length = 100)
    private String model;

    @Id
    @NotBlank(message = "Variant is required")
    @Column(length = 100)
    private String variant;

    @Column(name = "full_model_name", insertable = false, updatable = false)
    private String fullModelName;
}

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class AircraftModelId implements Serializable {
    @Column(nullable = false, length = 255)
    private String manufacturer;
    @Column(nullable = false, length = 255)
    private String model;
    @Column(nullable = false, length = 255)
    private String variant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null | getClass() != o.getClass()) return false;
        AircraftModelId that = (AircraftModelId) o;
        return manufacturer.equals(that.getManufacturer()) &&
                model.equals(that.getModel()) &&
                variant.equals(that.getVariant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, variant);
    }
}
