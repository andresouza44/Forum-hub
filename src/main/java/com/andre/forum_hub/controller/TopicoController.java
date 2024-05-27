package com.andre.forum_hub.controller;

import com.andre.forum_hub.dto.TopicoDto;
import com.andre.forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    public ResponseEntity<TopicoDto> create (@RequestBody @Valid TopicoDto dto){
        TopicoDto dtoResult = service.cadastrar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dtoResult.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dtoResult);

    }

}
