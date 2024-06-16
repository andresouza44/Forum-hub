package com.andre.forum_hub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String mensagem;
    
    private LocalDateTime dataCriacao;
    private Boolean status;

    private String curso;
    private String autor;

//    @ManyToOne
//    @JoinColumn(name ="curso_id")
//    private Curso curso;
//
//    @ManyToOne
//    @JoinColumn(name = "autor_id")
//    private Usuario autor;
//
    @OneToMany (mappedBy = "topico" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resposta> respostas;

}
