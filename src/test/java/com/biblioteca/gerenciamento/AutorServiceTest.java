package com.biblioteca.gerenciamento;

import com.biblioteca.gerenciamento.autor.dtos.AutorInDTO;
import com.biblioteca.gerenciamento.autor.dtos.AutorOutDTO;
import com.biblioteca.gerenciamento.autor.entity.AutorEntity;
import com.biblioteca.gerenciamento.autor.mapper.AutorMapper;
import com.biblioteca.gerenciamento.autor.repository.AutorRepository;
import com.biblioteca.gerenciamento.autor.service.AutorService;
import com.biblioteca.gerenciamento.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {
    @Mock
    AutorRepository repository;
    @Mock
    AutorMapper mapper;
    @InjectMocks
    AutorService service;

@Test
void deveCriarAutorComSucesso (){
    AutorInDTO autorInDTO = new AutorInDTO();
    AutorEntity autorEntity = new AutorEntity();
    AutorOutDTO autorOutDTO = new AutorOutDTO();

    when(mapper.paraEntity(autorInDTO)).thenReturn(autorEntity);
    when(repository.save(any(AutorEntity.class))).thenReturn(autorEntity);
    when(mapper.paraDTO(autorEntity)).thenReturn(autorOutDTO);

    AutorOutDTO resultado = service.criarAutor(autorInDTO);

    assertNotNull(resultado);
    verify(repository, times(1)).save(any());
}
    @Test
    void deveLancarExceptionQuandoIdNaoExistir() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.buscarAutorPorId(1L)
        );
    }

    @Test
    void deveAtualizarAutorComSucesso() {

        Long id = 1L;

        AutorInDTO inDTO = new AutorInDTO();
        inDTO.setNome("Novo Nome");

        AutorEntity entity = new AutorEntity();

        AutorOutDTO outDTO = new AutorOutDTO();

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        when(mapper.paraDTO(entity))
                .thenReturn(outDTO);

        AutorOutDTO resultado =
                service.atualizarDadosAutor(inDTO, id);

        assertNotNull(resultado);

        verify(repository, times(1))
                .save(any(AutorEntity.class));
    }
    @Test
    void deveDeletarAutorComSucesso() {

        Long id = 1L;

        AutorEntity entity = new AutorEntity();
        entity.setObras(new ArrayList<>());

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        AutorEntity resultado =
                service.deletarAutorPorId(id);

        assertNotNull(resultado);

        verify(repository, times(1))
                .delete(entity);
    }
    @Test
    void deveLancarExceptionQuandoCpfBrasileiroForInvalido() {

        AutorInDTO inDTO = new AutorInDTO();

        inDTO.setPaisOrigem("Brasil");
        inDTO.setCpf(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.validarCpfBrasileiro(inDTO)
        );
    }
}
