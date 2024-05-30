package com.andre.forum_hub.dto;

import com.andre.forum_hub.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class RoleDto {
    Long id;
    String authority;

    public RoleDto(){

    }

    public RoleDto(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDto (Role role){
        this.id = role.getId();
        this.authority = role.getAuthority();

    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }
}
