package com.gaw.AeroDocs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Table(name = "aircraft_models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AircraftModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "full_model_name", insertable = false, updatable = false)    private String fullModelName;

    @NotBlank(message = "Manufacturer is required")
    @Column(length = 255, nullable = false)
    private String manufacturer;

    @NotBlank(message = "Model is required")
    @Column(length = 255, nullable = false)
    private String model;

    @NotBlank(message = "Variant is required")
    @Column(length = 255, nullable = false)
    private String variant;

    @ManyToMany(mappedBy = "favoriteAircraftModels", fetch = FetchType.LAZY) // alternatively use @JsonIgnore
    private Set<User> favoritedByUsers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null | getClass() != o.getClass())
            return false;
        AircraftModel that = (AircraftModel) o;
        return fullModelName.equals(that.getFullModelName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullModelName);
    }
}
