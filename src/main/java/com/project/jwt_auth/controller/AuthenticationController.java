package com.project.jwt_auth.controller;

import com.project.jwt_auth.dao.User;
import com.project.jwt_auth.dto.LoginUserDto;
import com.project.jwt_auth.dto.RegisterUserDto;
import com.project.jwt_auth.repository.UserRepository;
import com.project.jwt_auth.responses.LoginResponse;
import com.project.jwt_auth.serviceImpl.AuthenticationService;
import com.project.jwt_auth.serviceImpl.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController
{
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    public AuthenticationController(JwtService jwtService,
                                    AuthenticationService authenticationService,
                                    UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto)
    {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getJwtExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
