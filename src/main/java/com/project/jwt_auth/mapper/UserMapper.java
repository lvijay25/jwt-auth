package com.project.jwt_auth.mapper;

import com.project.jwt_auth.dao.User;
import com.project.jwt_auth.dto.UserDTO;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        return userDTO;
    }
}
