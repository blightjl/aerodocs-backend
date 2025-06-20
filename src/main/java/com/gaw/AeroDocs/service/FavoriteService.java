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
    public User addFavoriteModel(String username, String fullModelName) {
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
            return user;
        }
        user.addFavoriteAircraftModel(model);
        return userRepository.save(user);
    }

    @Transactional
    public User removeFavoriteModel(String username, String fullModelName) {
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

        if (!user.getFavoriteAircraftModels().contains(model)) {
            return user;
        }
        user.removeFavoriteAircraftModel(model);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Set<AircraftModel> getUserFavoriteModels(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            return Set.of();
        }
        User user = optionalUser.get();

        return user.getFavoriteAircraftModels();
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
