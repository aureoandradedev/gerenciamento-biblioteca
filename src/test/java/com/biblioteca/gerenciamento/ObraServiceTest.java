package com.biblioteca.gerenciamento;

import com.biblioteca.gerenciamento.autor.repository.AutorRepository;
import com.biblioteca.gerenciamento.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.obra.dtos.ObraInDTO;
import com.biblioteca.gerenciamento.obra.dtos.ObraOutDTO;
import com.biblioteca.gerenciamento.obra.entity.ObraEntity;
import com.biblioteca.gerenciamento.obra.mapper.ObraMapper;
import com.biblioteca.gerenciamento.obra.repository.ObraRepository;
import com.biblioteca.gerenciamento.obra.service.ObraService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ObraServiceTest {
    @Mock
    ObraRepository repository;
    @Mock
    ObraMapper mapper;
    @InjectMocks
    ObraService service;

    @Mock
    AutorRepository autorRepository;

    @Test
    void criarObraComSucesso() {
        ObraInDTO obraInDTO = new ObraInDTO();
        obraInDTO.setDataPublicacao(LocalDateTime.now());

        ObraEntity obraEntity = new ObraEntity();
        ObraOutDTO obraOutDTO = new ObraOutDTO();

        when(mapper.paraEntity(obraInDTO)).thenReturn(obraEntity);
        when(repository.save(any(ObraEntity.class))).thenReturn(obraEntity);
        when(mapper.paraDTO(obraEntity)).thenReturn(obraOutDTO);
        when(autorRepository.findAllById(any()))
                .thenReturn(new ArrayList<>());
        ObraOutDTO resultado = service.criarObra(obraInDTO);

        assertNotNull(resultado);
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveLancarExceptionQuandoIdNaoExistir() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.buscarObraPorId(1L)
        );
    }

    @Test
    void deveAtualizarObraComSucesso() {

        Long id = 1L;

        ObraInDTO obraInDTO = new ObraInDTO();
        obraInDTO.setNome("Novo Nome");

        ObraEntity obraEntity = new ObraEntity();

        ObraOutDTO obraOutDTO = new ObraOutDTO();

        when(repository.findById(id))
                .thenReturn(Optional.of(obraEntity));

        when(mapper.paraDTO(obraEntity))
                .thenReturn(obraOutDTO);

        ObraOutDTO resultado =
                service.atualizarDadosObraPorId(obraInDTO, id);

        assertNotNull(resultado);

        verify(repository, times(1))
                .save(any(ObraEntity.class));
    }

    @Test
    void deveDeletarObraComSucesso() {

        Long id = 1L;

        ObraEntity entity = new ObraEntity();
        entity.setAutores(new ArrayList<>());

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        ObraEntity resultado =
                service.deletarObraPorId(id);

        assertNotNull(resultado);

        verify(repository, times(1)).delete(entity);

    }

    @Test
    void deveLancarExceptionQuandoDatasForemNulas() {

        ObraInDTO inDTO = new ObraInDTO();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.validarDatas(inDTO)
        );
    }
}