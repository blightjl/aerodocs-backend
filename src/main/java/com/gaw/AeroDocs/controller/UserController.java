package com.gaw.AeroDocs.controller;

import java.util.List;
import java.util.Optional;

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

import com.gaw.AeroDocs.entity.User;
import com.gaw.AeroDocs.service.UserService;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        if (this.userService.userExists(user.getUsername())) {
            return ResponseEntity.status(409).body(user);
        }
        User createdUser = this.userService.registerUser(user);
        if (createdUser == null) {
            return ResponseEntity.status(400).body(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = this.userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = this.userService.getUser(username);
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Integer> deleteUser(@PathVariable String username) {
        if (this.userService.deleteUser(username)) {
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(200).build();
    }
}
