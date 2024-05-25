package com.andre.forum_hub.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "tb_resposta")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;
    private LocalDateTime dataCriacao;
    private String solucao;

    @ManyToOne
    @JoinColumn(name="topico_id")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name="autor_id")
    private  Usuario autor;
}
