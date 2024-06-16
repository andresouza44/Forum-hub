package com.andre.forum_hub.controller;

import com.andre.forum_hub.dto.RespostaDto;
import com.andre.forum_hub.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    RespostaService service;

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(value = "/{id}")
    public ResponseEntity<RespostaDto> novaResposta(@PathVariable Long id, @RequestBody RespostaDto dto){
        System.out.println(id);
        RespostaDto respostaDto = service.novaResposta(dto, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(respostaDto);

    }
}
