package com.andre.forum_hub.entity;

import jakarta.persistence.*;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Entity
@Table (name = "tb_user")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @ManyToMany
    @JoinTable(name = "tb_usuario_perfil",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn (name = "perfil_id"))
    private Set<Perfil> perfis = new HashSet<>();




}
