package com.biblioteca.gerenciamento.autor.controller;

import com.biblioteca.gerenciamento.autor.dtos.AutorInDTO;
import com.biblioteca.gerenciamento.autor.dtos.AutorOutDTO;
import com.biblioteca.gerenciamento.autor.entity.AutorEntity;
import com.biblioteca.gerenciamento.autor.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping ("/autor")
public class AutorController {
    private final AutorService service;

    @PostMapping
    public ResponseEntity <AutorOutDTO> criarAutor (@Valid @RequestBody AutorInDTO autorInDTO){
        return ResponseEntity.ok(service.criarAutor(autorInDTO));
    }

    @GetMapping ("/{id}")
    public ResponseEntity <AutorEntity> buscarAutorPorId (@Valid @PathVariable long id){
       return ResponseEntity.ok(service.buscarAutorPorId(id));
    }

    @PatchMapping ("/atualizar/{id}")
    public ResponseEntity <AutorOutDTO> atualizarDadosAutorPorId (@PathVariable long id, @RequestBody AutorInDTO autorInDTO){
        return  ResponseEntity.ok(service.atualizarDadosAutor(autorInDTO,id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity <AutorEntity> deletarAutorPorId (@PathVariable long id){
        return ResponseEntity.ok(service.deletarAutorPorId(id));
    }
}
