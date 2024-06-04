package com.andre.forum_hub.dto;

import com.andre.forum_hub.entity.Role;

import java.util.List;

public record RegisterDTO(String name, String email, String password, List<Role> roles) {


}
