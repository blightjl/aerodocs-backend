package com.gaw.AeroDocs.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaw.AeroDocs.entity.AircraftModel;
import com.gaw.AeroDocs.dto.AircraftModelDTO;

import com.gaw.AeroDocs.entity.User;
import com.gaw.AeroDocs.service.AircraftModelService;

@RestController
public class AircraftModelController {
    AircraftModelService aircraftModelService;

    @Autowired
    public AircraftModelController(AircraftModelService aircraftModelService) {
        this.aircraftModelService = aircraftModelService;
    }

    @PostMapping("/aircrafts")
    public ResponseEntity<AircraftModel> postAircraftModel(@RequestBody AircraftModel aircraftModel) {
        if (this.aircraftModelService.fullModelNameExists(aircraftModel.getManufacturer(), aircraftModel.getModel(), aircraftModel.getVariant())) {
            return ResponseEntity.status(409).body(aircraftModel);
        }
        AircraftModel createdAircraftModel = this.aircraftModelService.addAircraftModel(aircraftModel);
        if (createdAircraftModel == null) {
            return ResponseEntity.status(400).body(aircraftModel);
        }
        return ResponseEntity.status(HttpStatus.OK).body(aircraftModel);
    }

    @GetMapping("/aircrafts")
    public ResponseEntity<List<AircraftModelDTO>> getAircraftModels() {
        List<AircraftModel> aircraftModelsList = this.aircraftModelService.getAircraftModels();
        List<AircraftModelDTO> aircraftModelsDTOList = aircraftModelsList.stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(aircraftModelsDTOList);
    }

    @GetMapping("/aircrafts/{full_model_name}")
    public ResponseEntity<AircraftModelDTO> getAircraftModel(@PathVariable String full_model_name) {
        Optional<AircraftModel> aircraftModel = this.aircraftModelService.getAircraftModel(full_model_name);
        return ResponseEntity.status(HttpStatus.OK).body(aircraftModel.isPresent() ? this.toDTO(aircraftModel.get()) : null);
    }

    @DeleteMapping("/aircrafts/{full_model_name}")
    public ResponseEntity<Integer> deleteAircraftModel(@PathVariable String full_model_name) {
        if (this.aircraftModelService.deleteAircraftModel(full_model_name)) {
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(200).build();
    }

    private AircraftModelDTO toDTO(AircraftModel aircraftModel) {
        return new AircraftModelDTO(aircraftModel.getManufacturer(), aircraftModel.getModel(), aircraftModel.getVariant(), aircraftModel.getFullModelName());
    }
}
