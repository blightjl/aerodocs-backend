package com.gaw.AeroDocs.service;

import org.springframework.stereotype.Service;

import com.gaw.AeroDocs.entity.User;
import com.gaw.AeroDocs.repository.UserRepository;
import com.gaw.AeroDocs.dto.UserDTO;
import com.gaw.AeroDocs.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO registerUser(User user) {
        User savedUser = this.userRepository.save(user);
        return UserMapper.toUserDTO(savedUser);
    }

    public boolean userExists(String username) {
        return this.userRepository.existsById(username);
    }

    public List<UserDTO> getUsers() {
        return this.userRepository.findAll().stream().map(user -> UserMapper.toUserDTO(user))
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUser(String username) {
        Optional<User> optionalUser = this.userRepository.findById(username);
        return optionalUser.isPresent() ? Optional.of(UserMapper.toUserDTO(optionalUser.get())) : Optional.empty();
    }

    public boolean deleteUser(String username) {
        if (!this.userRepository.existsById(username)) {
            return false;
        }
        this.userRepository.deleteById(username);
        return true;
    }
}
