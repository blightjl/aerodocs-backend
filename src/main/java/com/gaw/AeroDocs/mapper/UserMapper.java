package com.gaw.AeroDocs.mapper;

import com.gaw.AeroDocs.entity.User;
import com.gaw.AeroDocs.dto.UserDTO;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getEmail());
    }
}
