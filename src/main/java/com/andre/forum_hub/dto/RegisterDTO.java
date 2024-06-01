package com.andre.forum_hub.dto;

import com.andre.forum_hub.entity.Role;

public record RegisterDTO(String name, String email, String password, Role role) {


}
