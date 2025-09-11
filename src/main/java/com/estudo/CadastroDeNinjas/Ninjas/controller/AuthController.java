package com.estudo.CadastroDeNinjas.Ninjas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.CadastroDeNinjas.Ninjas.model.Role;
import com.estudo.CadastroDeNinjas.Ninjas.model.UserAccount;
import com.estudo.CadastroDeNinjas.Ninjas.repository.UserAccountRepository;
import com.estudo.CadastroDeNinjas.Ninjas.security.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserAccountRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> payload){
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");
        String role = (String) payload.getOrDefault("role", "USER");

        UserAccount user = new UserAccount();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.getRoles().add(Role.valueOf(role.toUpperCase()));
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload){
        String username = payload.get("username");
        String password = payload.get("password");

        UserAccount user = userRepo.findByUsername(username).orElse(null);
        if (user == null || !encoder.matches(password, user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }
}


