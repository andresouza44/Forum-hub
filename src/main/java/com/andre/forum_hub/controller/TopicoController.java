package com.andre.forum_hub.controller;

import com.andre.forum_hub.dto.TopicoDto;
import com.andre.forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    public ResponseEntity<TopicoDto> create(@RequestBody @Valid TopicoDto dto) {
        TopicoDto dtoResult = service.cadastrar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dtoResult.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dtoResult);

    }

    @GetMapping
    public ResponseEntity<Page<TopicoDto>> findAll(@PageableDefault(size=10,sort = "dataCriacao") Pageable pageable,
                                                   @RequestParam(name ="curso", defaultValue = "") String curso,
                                                   @RequestParam(name="ano", defaultValue = "") Integer ano) {
        Page<TopicoDto> topicos = service.findAll(pageable, curso, ano);
        return ResponseEntity.ok().body(topicos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicoDto> findById(@PathVariable Long id){
        TopicoDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TopicoDto> update (@RequestBody @Valid TopicoDto dto, @PathVariable  Long id){
        dto = service.update(dto, id);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete (@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
