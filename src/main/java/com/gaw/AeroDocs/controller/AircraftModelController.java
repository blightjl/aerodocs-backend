// package com.gaw.AeroDocs.controller;

// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

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
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.gaw.AeroDocs.entity.AircraftModel;
// import com.gaw.AeroDocs.service.AircraftModelService;

// @RestController
// public class AircraftModelController {
//     AircraftModelService aircraftModelService;

//     @Autowired
//     public AircraftModelController(AircraftModelService aircraftModelService) {
//         this.aircraftModelService = aircraftModelService;
//     }

//     @PostMapping("/aircrafts")
//     public ResponseEntity<AircraftModel> postAircraftModel(@RequestBody AircraftModel aircraftModel) {
//         // TODO: add implementation
//         return ResponseEntity.status(HttpStatus.OK).body(aircraftModel);
//     }

//     @GetMapping("/aircrafts/{aircraft_model_id}")
//     public ResponseEntity<AircraftModel> getAircraftModel(@PathVariable UUID aircraft_model_id) {
//         // TODO: add implementation with Optional
//         return ResponseEntity.status(HttpStatus.OK);
//     }

//     @GetMapping("/aircrafts")
//     public ResponseEntity<Iterable<AircraftModel>> getAircraftModels() {
//         // TODO: add implementation with List
//         return ResponseEntity.status(HttpStatus.OK);
//     }

//     @PatchMapping("/aircrafts/{aircraft_model_id}")
//     public ResponseEntity<Integer> updateAircraftModel(@PathVariable UUID aircraft_model_id) {
//         if (this.aircraftModelService.updateMessage(Integer.parseInt(message_id), message)) {
//             return ResponseEntity.status(HttpStatus.OK).body(1);
//         } else {
//             return ResponseEntity.status(400).body(0);
//         }
//     }

//     @DeleteMapping("/aircrafts/{aircraft_model_id}")
//     public ResponseEntity<Integer> deleteAircraftModel(@PathVariable UUID aircraft_model_id) {
//         if (this.aircraftModelService.updateMessage(Integer.parseInt(message_id), message)) {
//             return ResponseEntity.status(HttpStatus.OK).body(1);
//         } else {
//             return ResponseEntity.status(400).body(0);
//         }
//     }
// }
