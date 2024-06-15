package com.andre.forum_hub.controller;


import com.andre.forum_hub.dto.UserDto;
import com.andre.forum_hub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDto> getMe() {
        UserDto userDto = service.getMe();
        return ResponseEntity.ok().body(userDto);

    }
}
