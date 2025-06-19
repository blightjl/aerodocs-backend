package com.gaw.AeroDocs.service;

import org.springframework.stereotype.Service;

import com.gaw.AeroDocs.entity.AircraftModel;
import com.gaw.AeroDocs.repository.AircraftModelRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AircraftModelService {
    private AircraftModelRepository aircraftModelRepository;

    @Autowired
    public AircraftModelService(AircraftModelRepository aircraftModelRepository) {
        this.aircraftModelRepository = aircraftModelRepository;
    }

    public AircraftModel addAircraftModel(AircraftModel aircraftModel) {
        return this.aircraftModelRepository.save(aircraftModel);
    }

    public boolean fullModelNameExists(String manufacturer, String model, String variant) {
        return this.aircraftModelRepository.findByManufacturerModelAndVariant(manufacturer, model, variant).isPresent();
    }

    // public boolean userExists(String username) {
    //     return this.userRepository.existsById(username);
    // }

    public List<AircraftModel> getAircraftModels() {
        return this.aircraftModelRepository.findAll();
    }

    // public Optional<User> getUser(String username) {
    //     return this.userRepository.findById(username);
    // }

    // public boolean deleteUser(String username) {
    //     if (!this.userRepository.existsById(username)) {
    //         return false;
    //     }
    //     this.userRepository.deleteById(username);
    //     return true;
    // }
}
