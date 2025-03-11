package com.tericcabrel.auth_api.controllers;

import com.tericcabrel.auth_api.dtos.LoginUserDto;
import com.tericcabrel.auth_api.dtos.RegistreUserDto;
import com.tericcabrel.auth_api.entities.User;
import com.tericcabrel.auth_api.responses.LoginResponse;
import com.tericcabrel.auth_api.services.AuthenticationService;
import com.tericcabrel.auth_api.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String teste(){
        return "That's OK";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegistreUserDto registreUserDto){
        User registeredUser = authenticationService.signup(registreUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/loginuser")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken,jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
