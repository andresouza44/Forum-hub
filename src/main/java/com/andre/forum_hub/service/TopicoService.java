package com.andre.forum_hub.service;

import com.andre.forum_hub.dto.TopicoDto;
import com.andre.forum_hub.entity.Topico;
import com.andre.forum_hub.repository.TopicoRepository;
import com.andre.forum_hub.validacoes.ValidadorTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository repository;

    @Autowired
    List<ValidadorTopico> validadores;

    public TopicoDto cadastrar (TopicoDto dto){

//        List<Topico> topicos = repository.findAll();
//        topicos.forEach(e -> {
//            if (e.getTitulo().equals(dto.getTitulo())) {
//                throw new ValidationException("Título já existe");
//            }
//        });

        validadores.forEach(v -> v.validar(dto));

        Topico topico = new Topico();

        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(true);
        topico.setAutor(dto.getAutor());
        topico.setCurso(dto.getCurso());

        repository.save(topico);

        return new TopicoDto(topico);



    }
}
