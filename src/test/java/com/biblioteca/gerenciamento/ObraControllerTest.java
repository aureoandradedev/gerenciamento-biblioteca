package com.biblioteca.gerenciamento;

import com.biblioteca.gerenciamento.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.obra.controller.ObraController;
import com.biblioteca.gerenciamento.obra.dtos.ObraInDTO;
import com.biblioteca.gerenciamento.obra.dtos.ObraOutDTO;
import com.biblioteca.gerenciamento.obra.entity.ObraEntity;
import com.biblioteca.gerenciamento.obra.service.ObraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ObraController.class)
public class ObraControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ObraService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarObraComSucesso() throws Exception {

        ObraInDTO inDTO = new ObraInDTO();

        inDTO.setNome("Livro");
        inDTO.setDescricao("Descricao");
        inDTO.setAutorIds(List.of(1L));
        inDTO.setDataPublicacao(LocalDateTime.now());

        ObraOutDTO outDTO = new ObraOutDTO();

        when(service.criarObra(any(ObraInDTO.class)))
                .thenReturn(outDTO);

        String json =
                objectMapper.writeValueAsString(inDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/obra")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarObraComSucesso() throws Exception {

        Long id = 1L;

        ObraInDTO inDTO = new ObraInDTO();

        ObraOutDTO outDTO = new ObraOutDTO();

        when(service.atualizarDadosObraPorId(any(ObraInDTO.class), anyLong()))
                .thenReturn(outDTO);

        String json =
                objectMapper.writeValueAsString(inDTO);


        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/obra/atualizar/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deveLancarExceptionQuandoIdNaoExistir() throws Exception {
        Long id = 1L;

        when(service.buscarObraPorId(id))
                .thenThrow(new ResourceNotFoundException("Id não encontrado"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/obra/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarObraCComSucesso() throws Exception {

        Long id = 1L;

        ObraEntity entity = new ObraEntity();
        entity.setAutores(new ArrayList<>());

        when(service.deletarObraPorId(id))
                .thenReturn(entity);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/obra/{id}", id))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExceptionAoDeletarAObra() throws Exception {

        Long id = 1L;

        when(service.deletarObraPorId(id))
                .thenThrow(
                        new ResourceNotFoundException("Id não encontrado")
                );

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/obra/{id}", id)
                )
                .andExpect(status().isNotFound());
    }
}

