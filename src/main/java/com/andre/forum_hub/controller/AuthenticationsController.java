package com.andre.forum_hub.controller;

import com.andre.forum_hub.config.TokenService;
import com.andre.forum_hub.dto.AuthenticationDTO;
import com.andre.forum_hub.dto.LoginResponseDTO;
import com.andre.forum_hub.dto.RegisterDTO;
import com.andre.forum_hub.entity.User;
import com.andre.forum_hub.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:8080")
public class AuthenticationsController {

    @Autowired
    UserRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (repository.findByEmail(data.email()) != null) return
            ResponseEntity.badRequest().build();
        System.out.println(data);
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.name(),data.email(), encryptedPassword);
        newUser.addRole(data.role());
        System.out.println(newUser);
        repository.save(newUser);


        return ResponseEntity.ok().build();
    }
}
