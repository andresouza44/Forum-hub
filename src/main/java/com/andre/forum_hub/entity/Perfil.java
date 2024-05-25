package com.andre.forum_hub.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="tb_perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
