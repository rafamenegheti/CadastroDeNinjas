package com.estudo.CadastroDeNinjas.Ninjas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudo.CadastroDeNinjas.Ninjas.model.Role;
import com.estudo.CadastroDeNinjas.Ninjas.model.UserAccount;
import com.estudo.CadastroDeNinjas.Ninjas.repository.UserAccountRepository;
import com.estudo.CadastroDeNinjas.Ninjas.security.JwtService;

@Service
public class AuthService {

    @Autowired 
    private UserAccountRepository userRepository;
    @Autowired 
    private PasswordEncoder encoder;
    @Autowired 
    private JwtService jwtService;

    public UserAccount registerUser(String username, String password, String role) {
        UserAccount user = new UserAccount();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.getRoles().add(Role.valueOf(role.toUpperCase()));
        userRepository.save(user);
        return user;
    }

    public String authenticate(String username, String password) {
        UserAccount user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtService.generateToken(user);
    }

}
