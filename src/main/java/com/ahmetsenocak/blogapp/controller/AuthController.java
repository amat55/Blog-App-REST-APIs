package com.ahmetsenocak.blogapp.controller;

import com.ahmetsenocak.blogapp.payload.JWTAuthResponse;
import com.ahmetsenocak.blogapp.payload.LoginDTO;
import com.ahmetsenocak.blogapp.payload.RegisterDTO;
import com.ahmetsenocak.blogapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login Rest-API
    @PostMapping(value = {"/login", "/signing"}) // User can use both url login or signing
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build register Rest-API
    @PostMapping(value = {"/register", "/signup"}) // User can use both url register or signup
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
