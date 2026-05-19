package com.biblioteca.gerenciamento.obra.controller;

import com.biblioteca.gerenciamento.obra.dtos.ObraInDTO;
import com.biblioteca.gerenciamento.obra.dtos.ObraOutDTO;
import com.biblioteca.gerenciamento.obra.entity.ObraEntity;
import com.biblioteca.gerenciamento.obra.service.ObraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/obra")
public class ObraController {
    private final ObraService service;

    @PostMapping
    public ResponseEntity <ObraOutDTO> criarObra (@Valid @RequestBody ObraInDTO obraInDTO){
        return ResponseEntity.ok(service.criarObra(obraInDTO));
    }

    @GetMapping ("/{id}")
    public ResponseEntity <ObraOutDTO> buscarObraPorId (@Valid @PathVariable Long id){
        return  ResponseEntity.ok(service.buscarObraPorId(id));
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity <ObraOutDTO> atualizarDadosObraPorId (@PathVariable long id, @RequestBody ObraInDTO obraInDTO){
        return ResponseEntity.ok(service.atualizarDadosObraPorId(obraInDTO, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity <ObraEntity> deletarObraPorId (@PathVariable long id){
        return ResponseEntity.ok(service.deletarObraPorId(id));
    }
}
