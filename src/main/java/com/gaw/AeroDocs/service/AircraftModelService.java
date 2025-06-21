package com.gaw.AeroDocs.service;

import org.springframework.stereotype.Service;

import com.gaw.AeroDocs.dto.AircraftModelDTO;
import com.gaw.AeroDocs.entity.AircraftModel;
import com.gaw.AeroDocs.entity.User;
import com.gaw.AeroDocs.repository.AircraftModelRepository;
import com.gaw.AeroDocs.mapper.AircraftMapper;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AircraftModelService {
    private AircraftModelRepository aircraftModelRepository;

    @Autowired
    public AircraftModelService(AircraftModelRepository aircraftModelRepository) {
        this.aircraftModelRepository = aircraftModelRepository;
    }

    public AircraftModelDTO addAircraftModel(AircraftModel aircraftModel) {
        AircraftModel savedAircraftModel = this.aircraftModelRepository.save(aircraftModel);
        return AircraftMapper.toAircraftDTO(savedAircraftModel);
    }

    public boolean fullModelNameExists(String manufacturer, String model, String variant) {
        return this.aircraftModelRepository.findByManufacturerModelAndVariant(manufacturer, model, variant).isPresent();
    }

    public List<AircraftModelDTO> getAircraftModels() {
        return this.aircraftModelRepository.findAll().stream().map(aircraftModel -> AircraftMapper.toAircraftDTO(aircraftModel))
                .collect(Collectors.toList());
    }

    public Optional<AircraftModelDTO> getAircraftModel(String fullModelName) {
        Optional<AircraftModel> optionalAircraftModel = this.aircraftModelRepository.findByFullModelName(fullModelName);
        return optionalAircraftModel.isPresent() ? Optional.of(AircraftMapper.toAircraftDTO(optionalAircraftModel.get())) : Optional.empty();
    }

    @Transactional
    public boolean deleteAircraftModel(String fullModelName) {
        if (!this.aircraftModelRepository.findByFullModelName(fullModelName).isPresent()) {
            return false;
        }
        this.aircraftModelRepository.deleteByModelIdentifier(fullModelName);
        return true;
    }
}
