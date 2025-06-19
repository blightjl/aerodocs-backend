package com.gaw.AeroDocs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gaw.AeroDocs.entity.AircraftModel;

@Repository
public interface AircraftModelRepository extends JpaRepository<AircraftModel, String> {
    List<AircraftModel> findAll();

    @Query("SELECT a from AircraftModel a WHERE a.manufacturer = :manufacturer AND a.model = :model AND a.variant = :variant")
    Optional<AircraftModel> findByManufacturerModelAndVariant(@Param("manufacturer") String manufacturer, @Param("model") String model, @Param("variant") String variant);
}
