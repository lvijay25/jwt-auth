package com.project.jwt_auth.controller;

import com.project.jwt_auth.dao.User;
import com.project.jwt_auth.dto.UserDTO;
import com.project.jwt_auth.serviceImpl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController
{
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/get")
    public ResponseEntity<User> getAuthenticatedUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<UserDTO> getRegisteredUser(@PathVariable("username") String username)
    {
        UserDTO userDTO = userServiceImpl.getUser(username);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> userDTO = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(userDTO);
    }

}
