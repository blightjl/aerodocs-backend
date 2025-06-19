// package com.gaw.AeroDocs.controller;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.gaw.AeroDocs.entity.AircraftModel;
// import com.gaw.AeroDocs.entity.User;
// import com.gaw.AeroDocs.service.UserFavoriteService;
// import com.gaw.AeroDocs.service.UserService;

// @RestController
// @RequestMapping("/users/{user_id}/favorites")
// public class UserFavoriteController {
//     UserFavoriteService userFavoriteService;

//     @Autowired
//     public UserFavoriteController(UserFavoriteService userFavoriteService) {
//         this.userFavoriteService = userFavoriteService;
//     }

//     @GetMapping
//     public ResponseEntity<Iterable<AircraftModel>> getUserFavorites(@PathVariable UUID user_id) {
//         Iterable<AircraftModel> favorites = userFavoriteService.getUserFavorites(user_id);
//         return ResponseEntity.ok(favorites);
//     }

//     @PostMapping
//     public ResponseEntity<AircraftModel> addFavorite(@PathVariable UUID user_id, @RequestBody AircraftModel aircraftModel) {
//         return ResponseEntity.status(HttpStatus.CREATED).body();
//     }

//     @PatchMapping
//     public ResponseEntity<AircraftModel> addFavorite(@PathVariable UUID user_id, @RequestBody AircraftModel aircraftModel) {
//         return ResponseEntity.status(HttpStatus.CREATED).body();
//     }

//     @DeleteMapping("/aircraft_model_id")
//     public ResponseEntity<AircraftModel> addFavorite(@PathVariable UUID user_id, @RequestBody AircraftModel aircraftModel) {
//         return ResponseEntity.status(HttpStatus.CREATED).body();
//     }
// }
