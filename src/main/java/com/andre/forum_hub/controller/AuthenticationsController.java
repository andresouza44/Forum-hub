package com.andre.forum_hub.controller;

import com.andre.forum_hub.dto.AuthenticationDTO;
import com.andre.forum_hub.dto.LoginResponseDTO;
import com.andre.forum_hub.dto.RegisterDTO;
import com.andre.forum_hub.dto.UserDto;
import com.andre.forum_hub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationsController {


    @Autowired
    UserService service;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO dto) {
        LoginResponseDTO responseDto = service.login(dto);

        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestBody @Valid RegisterDTO dto) {
        UserDto userDto = service.registerNewUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(userDto.getId()).toUri();

        return ResponseEntity.created(uri).body(userDto);
    }

//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
//        if (repository.findByEmail(data.email()) != null) return
//                ResponseEntity.badRequest().build();
//        System.out.println(data);
//        String encryptedPassword = passwordEncoder.encode(data.password());
//        User newUser = new User(data.name(), data.email(), encryptedPassword);
//        newUser.addRole(data.role());
//        System.out.println(newUser);
//        repository.save(newUser);
//
//
//        return ResponseEntity.ok().build();
//    }
}
