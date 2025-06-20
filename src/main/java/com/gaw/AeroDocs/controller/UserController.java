package com.gaw.AeroDocs.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

import com.gaw.AeroDocs.controller.AircraftModelController;
import com.gaw.AeroDocs.entity.AircraftModel;
import com.gaw.AeroDocs.entity.User;
import com.gaw.AeroDocs.dto.AircraftModelDTO;
import com.gaw.AeroDocs.dto.UserDTO;
import com.gaw.AeroDocs.service.UserService;
import com.gaw.AeroDocs.service.FavoriteService;


@RestController
public class UserController {
    UserService userService;
    FavoriteService favoriteService;

    @Autowired
    public UserController(UserService userService, FavoriteService favoriteService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> postUser(@RequestBody User user) {
        if (this.userService.userExists(user.getUsername())) {
            return ResponseEntity.status(409).body(this.toUserDTO(user));
        }
        User createdUser = this.userService.registerUser(user);
        if (createdUser == null) {
            return ResponseEntity.status(400).body(this.toUserDTO(user));
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.toUserDTO(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> userList = this.userService.getUsers();
        List<UserDTO> userDTOList = userList.stream().map(this::toUserDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        Optional<User> optionalUser = this.userService.getUser(username);
        return ResponseEntity.status(HttpStatus.OK).body(optionalUser.isPresent() ? this.toUserDTO(optionalUser.get()) : null);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Integer> deleteUser(@PathVariable String username) {
        if (this.userService.deleteUser(username)) {
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/users/{username}/favorites")
    public ResponseEntity<Set<AircraftModelDTO>> getUserFavorites(@PathVariable String username) {
        Set<AircraftModel> favoriteAircraftModels = this.favoriteService.getUserFavoriteModels(username);
        if (favoriteAircraftModels == null) {
            return ResponseEntity.notFound().build();
        }
        Set<AircraftModelDTO> favoriteAircraftModelDTOs = favoriteAircraftModels.stream().map(this::toAircraftDTO).collect(Collectors.toSet());
        return ResponseEntity.ok(favoriteAircraftModelDTOs);
    }

    @PostMapping("/users/{username}/favorites")
    public ResponseEntity<UserDTO> addFavorite(@PathVariable String username, @RequestBody String fullModelName) {
        System.out.println(fullModelName);
        User updatedUser = this.favoriteService.addFavoriteModel(username, fullModelName);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.toUserDTO(updatedUser));
    }

    @DeleteMapping("/users/{username}/favorites")
    public ResponseEntity<UserDTO> removeFavorite(@PathVariable String username, @RequestBody String fullModelName) {
        User updatedUser = this.favoriteService.removeFavoriteModel(username, fullModelName);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.toUserDTO(updatedUser));
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getEmail());
    }

    private AircraftModelDTO toAircraftDTO(AircraftModel aircraftModel) {
        return new AircraftModelDTO(aircraftModel.getManufacturer(), aircraftModel.getModel(), aircraftModel.getVariant(), aircraftModel.getFullModelName());
    }
}
