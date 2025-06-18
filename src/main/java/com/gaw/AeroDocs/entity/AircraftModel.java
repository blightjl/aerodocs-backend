package com.gaw.AeroDocs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "aircraft_models", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "manufacturer", "model", "variant" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString()
public class AircraftModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "manufacturer", nullable = false)
    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @Column(name = "model", nullable = false)
    @NotBlank(message = "Model is required")
    private String model;
    
    @Column(name = "variant")
    private String variant;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(manufacturer).append(" ").append(model);
        if (variant != null && !variant.trim().isEmpty()) {
            fullName.append("-").append(variant);
        }
        return fullName.toString();
    }
}
