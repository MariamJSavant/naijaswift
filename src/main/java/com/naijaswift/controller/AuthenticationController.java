package com.naijaswift.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naijaswift.config.JwtService;
import com.naijaswift.dto.AuthResponse;
import com.naijaswift.dto.LoginRequest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
private final  AuthenticationManager authenticationManager;
private final JwtService jwtService;

public AuthenticationController(AuthenticationManager authenticationManager,JwtService jwtService){
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
}

@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );
    String token = jwtService.generateToken(new HashMap<>(), request.email());
    return ResponseEntity.ok(new AuthResponse(token));

}
    
}