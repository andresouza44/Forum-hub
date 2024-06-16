package com.andre.forum_hub.dto;

import com.andre.forum_hub.entity.Resposta;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespostaDto {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Long userId;
    private Long topicoId;

    public RespostaDto(Resposta resposta){
        id = resposta.getId();
        mensagem = resposta.getMensagem();
        dataCriacao = resposta.getDataCriacao();
        userId = resposta.getAutor().getId();
        topicoId = resposta.getTopico().getId();

    }
}
