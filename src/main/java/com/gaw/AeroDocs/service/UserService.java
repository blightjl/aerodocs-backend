package com.gaw.AeroDocs.service;

import org.springframework.stereotype.Service;

import com.gaw.AeroDocs.entity.User;
import com.gaw.AeroDocs.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return this.userRepository.save(user);
    }

    public boolean userExists(String username) {
        return this.userRepository.existsById(username);
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return this.userRepository.findById(username);
    }

    public boolean deleteUser(String username) {
        if (!this.userRepository.existsById(username)) {
            return false;
        }
        this.userRepository.deleteById(username);
        return true;
    }
}
