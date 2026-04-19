package com.project.jwt_auth.service;

import com.project.jwt_auth.dto.UserDTO;

import java.util.List;

public interface UserService
{
    UserDTO getUser(String username);
    List<UserDTO> getAllUsers();
}
