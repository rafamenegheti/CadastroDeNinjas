package com.estudo.CadastroDeNinjas.Ninjas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
import com.estudo.CadastroDeNinjas.Ninjas.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

 
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> payload){
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");
        String role = (String) payload.getOrDefault("role", "USER");

        authService.registerUser(username, password, role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload){
        String username = payload.get("username");
        String password = payload.get("password");

        String token = authService.authenticate(username, password);
        return ResponseEntity.ok(Map.of("token", token));
    }
}


