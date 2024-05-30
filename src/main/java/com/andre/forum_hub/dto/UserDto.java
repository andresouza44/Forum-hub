package com.andre.forum_hub.dto;

import com.andre.forum_hub.entity.Role;
import com.andre.forum_hub.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private Long id;

    @NotBlank(message = "o campo não pode estar em branco")
    private String nome;

    @Email(message = "Email inválido")
    private String email;
    private Set<RoleDto> roles = new HashSet<>();

    public UserDto (){
    }

    public UserDto(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();

        user.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }
}
