package com.project.jwt_auth.serviceImpl;

import com.project.jwt_auth.dao.User;
import com.project.jwt_auth.dto.UserDTO;
import com.project.jwt_auth.mapper.UserMapper;
import com.project.jwt_auth.repository.UserRepository;
import com.project.jwt_auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUser(String username)
    {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username is not found"));
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers()
    {
        List<UserDTO> allUsers = new ArrayList<>();
        userRepository.findAll()
                .forEach(user -> allUsers.add(UserMapper.mapToUserDTO(user)));
        return allUsers;
    }

}
