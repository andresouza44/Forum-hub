package com.andre.forum_hub.validacoes;

import com.andre.forum_hub.dto.TopicoDto;
import com.andre.forum_hub.entity.Topico;
import com.andre.forum_hub.repository.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarTituloRepetido implements ValidadorTopico {

    @Autowired
    TopicoRepository repository;

    @Override
    public void validar(TopicoDto dto) {
        List<Topico> topicos = repository.findAll();
        topicos.forEach(e -> {
            if (e.getTitulo().equals(dto.getTitulo())) {
                throw new ValidationException("Título já existe");
            }
        });
    }
}
