package com.andre.forum_hub.controller;

import com.andre.forum_hub.dto.AuthenticationDto;
import com.andre.forum_hub.dto.LoginResponseDto;
import com.andre.forum_hub.dto.RegisterDto;
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
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    UserService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto dto) {
        System.out.println(dto);
        LoginResponseDto responseDto = service.login(dto);

        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestBody @Valid RegisterDto dto) {
        UserDto userDto = service.registerNewUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(userDto.getId()).toUri();

        return ResponseEntity.created(uri).body(userDto);
    }

}
