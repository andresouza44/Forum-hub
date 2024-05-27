package com.andre.forum_hub.dto;


import com.andre.forum_hub.entity.Resposta;
import com.andre.forum_hub.entity.Topico;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class TopicoDto {
    private Long id;

    @NotBlank(message = "Campo título não pode estar vazio")
    private String titulo;

    @NotBlank(message = "Campo mensagem não pode estar vazio")
    private String mensagem;

    private LocalDateTime dataCriacao;
    private Boolean status;

    @NotBlank(message = "Campo curso não pode estar vazio")
    private String curso;
  //  private Curso curso;

    @NotBlank(message = "Campo autor não pode estar vazio")
    private String autor;
    //private Usuario autor;

    private List<Resposta> respostas;

    public TopicoDto() {
    }

    public TopicoDto(Long id,String titulo, String mensagem, String curso, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.curso = curso;
        this.autor = autor;
    }

    public TopicoDto(Topico topico) {
        id = topico.getId();
        titulo = topico.getTitulo();
        mensagem = topico.getMensagem();
        dataCriacao = topico.getDataCriacao();
        status = topico.getStatus();
        autor = topico.getAutor();
        curso = topico.getCurso();

    }
}
