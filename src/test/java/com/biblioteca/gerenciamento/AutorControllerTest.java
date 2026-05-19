package com.biblioteca.gerenciamento;

import com.biblioteca.gerenciamento.autor.controller.AutorController;
import com.biblioteca.gerenciamento.autor.dtos.AutorInDTO;
import com.biblioteca.gerenciamento.autor.dtos.AutorOutDTO;
import com.biblioteca.gerenciamento.autor.entity.AutorEntity;
import com.biblioteca.gerenciamento.autor.service.AutorService;
import com.biblioteca.gerenciamento.exceptions.ResourceNotFoundException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutorController.class)
public class AutorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AutorService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarAutorComSucesso() throws Exception {

        AutorInDTO inDTO = new AutorInDTO();

        inDTO.setNome("Aureo");
        inDTO.setSexo("Masculino");
        inDTO.setEmail("aureo@gmail.com");
        inDTO.setPaisOrigem("Brasil");
        inDTO.setCpf("12345678900");
        inDTO.setDataNascimento(LocalDateTime.now());

        AutorOutDTO outDTO = new AutorOutDTO();

        when(service.criarAutor(any(AutorInDTO.class)))
                .thenReturn(outDTO);

        String json =
                objectMapper.writeValueAsString(inDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/autor")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarAutorComSucesso() throws Exception {

        Long id = 1L;

        AutorInDTO inDTO = new AutorInDTO();

        AutorOutDTO outDTO = new AutorOutDTO();

        when(service.atualizarDadosAutor(any(AutorInDTO.class), anyLong()))
                .thenReturn(outDTO);

        String json =
                objectMapper.writeValueAsString(inDTO);


        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/autor/atualizar/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deveLancarExceptionQuandoIdNaoExistir() throws Exception {
        Long id = 1L;

        when(service.buscarAutorPorId(id))
                .thenThrow(new ResourceNotFoundException("Id não encontrado"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/autor/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarAutorComSucesso() throws Exception {

        Long id = 1L;

        AutorEntity entity = new AutorEntity();
        entity.setObras(new ArrayList<>());

        when(service.deletarAutorPorId(id))
                .thenReturn(entity);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/autor/{id}", id))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExceptionAoDeletarAutor() throws Exception {

        Long id = 1L;

        when(service.deletarAutorPorId(id))
                .thenThrow(
                        new ResourceNotFoundException("Id não encontrado")
                );

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/autor/{id}", id)
                )
                .andExpect(status().isNotFound());
    }
}
