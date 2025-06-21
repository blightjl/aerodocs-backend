package com.gaw.AeroDocs.service;

import com.gaw.AeroDocs.entity.AircraftModel;
import com.gaw.AeroDocs.entity.User;
import com.gaw.AeroDocs.repository.AircraftModelRepository;
import com.gaw.AeroDocs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.gaw.AeroDocs.dto.AircraftModelDTO;
import com.gaw.AeroDocs.dto.UserDTO;

import com.gaw.AeroDocs.mapper.UserMapper;
import com.gaw.AeroDocs.mapper.AircraftMapper;


@Service
public class FavoriteService {
    private UserRepository userRepository;
    private AircraftModelRepository aircraftModelRepository;
    
    @Autowired
    public FavoriteService(UserRepository userRepository, AircraftModelRepository aircraftModelRepository) {
        this.userRepository = userRepository;
        this.aircraftModelRepository = aircraftModelRepository;
    }

    @Transactional
    public UserDTO addFavoriteModel(String username, String fullModelName) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();

        Optional<AircraftModel> optionalModel = aircraftModelRepository.findByFullModelName(fullModelName);
        if (optionalModel.isEmpty()) {
            return null;
        }

        AircraftModel model = optionalModel.get();

        if (user.getFavoriteAircraftModels().contains(model)) {
            return UserMapper.toUserDTO(user);
        }
        user.addFavoriteAircraftModel(model);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserDTO(savedUser);
    }

    @Transactional
    public UserDTO removeFavoriteModel(String username, String fullModelName) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(username);
        System.out.println(fullModelName);
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Failed to find " + username);
            return null;
        }
        User user = optionalUser.get();

        Optional<AircraftModel> optionalModel = aircraftModelRepository.findByFullModelName(fullModelName);
        if (optionalModel.isEmpty()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Failed to find " + fullModelName);
            return null;
        }
        AircraftModel model = optionalModel.get();

        if (!user.getFavoriteAircraftModels().contains(model)) {
            return UserMapper.toUserDTO(user);
        }
        user.removeFavoriteAircraftModel(model);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Removed " + fullModelName);
        User savedUser = userRepository.save(user);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Saved " + username);
        return UserMapper.toUserDTO(savedUser);
    }

    @Transactional(readOnly = true)
    public Set<AircraftModelDTO> getUserFavoriteModels(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            return Set.of();
        }
        User user = optionalUser.get();

        return user.getFavoriteAircraftModels().stream().map(AircraftMapper::toAircraftDTO).collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    public boolean isFavorite(String username, String fullModelName) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();

        return user.getFavoriteAircraftModels().stream().allMatch(model -> model.getFullModelName().equals(fullModelName));
    }
}
