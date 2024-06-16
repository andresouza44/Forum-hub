package com.andre.forum_hub.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "tb_resposta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String solucao;

    @ManyToOne
    @JoinColumn(name="topico_id")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name="autor_id")
    private User autor;
}
