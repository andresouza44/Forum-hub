package com.andre.forum_hub.service;

import com.andre.forum_hub.dto.RespostaDto;
import com.andre.forum_hub.entity.Resposta;
import com.andre.forum_hub.entity.Topico;
import com.andre.forum_hub.entity.User;
import com.andre.forum_hub.repository.RespostaRepository;
import com.andre.forum_hub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RespostaService {

    @Autowired
    RespostaRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    TopicoService topicoService;

    @Autowired
    TopicoRepository topicoRepository;


    @Transactional
    public RespostaDto novaResposta(RespostaDto dto, Long topico_id) {
        Resposta resposta = new Resposta();
        User user = userService.authenticated();
        Topico topico = topicoService.findTopicoById(topico_id);

        resposta.setAutor(user);
        resposta.setTopico(topico);
        resposta.setMensagem(dto.getMensagem());
        resposta.setDataCriacao(LocalDateTime.now());
        repository.save(resposta);

        topico.getRespostas().add(resposta);
        topicoRepository.save(topico);

        return new RespostaDto(resposta);
    }
}
