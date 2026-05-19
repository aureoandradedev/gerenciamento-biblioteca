package com.biblioteca.gerenciamento.obra.service;

import com.biblioteca.gerenciamento.autor.entity.AutorEntity;
import com.biblioteca.gerenciamento.autor.repository.AutorRepository;
import com.biblioteca.gerenciamento.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.obra.dtos.ObraInDTO;
import com.biblioteca.gerenciamento.obra.dtos.ObraOutDTO;
import com.biblioteca.gerenciamento.obra.entity.ObraEntity;
import com.biblioteca.gerenciamento.obra.mapper.ObraMapper;
import com.biblioteca.gerenciamento.obra.repository.ObraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ObraService {
    private final ObraRepository repository;
    private final ObraMapper mapper;
    private final AutorRepository autorRepository;

    public ObraOutDTO criarObra(ObraInDTO obraInDTO) {
        List<AutorEntity> autores =
                autorRepository.findAllById(obraInDTO.getAutorIds());
        ObraEntity entity = mapper.paraEntity(obraInDTO);
        validarDatas(obraInDTO);
        entity.setAutores(autores);
        repository.save(entity);
        ObraOutDTO obraOutDTO = mapper.paraDTO(entity);
        return obraOutDTO;
    }

    public ObraOutDTO atualizarDadosObraPorId(ObraInDTO obraInDTO, long id) {
        ObraEntity entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id não encontrado "));
        if (obraInDTO.getAutorIds() != null) {

            List<AutorEntity> autores =
                    autorRepository.findAllById(obraInDTO.getAutorIds());

            entity.setAutores(autores);
        }
        if (obraInDTO.getNome() != null) {
            entity.setNome(obraInDTO.getNome());
        }
        if (obraInDTO.getDescricao() != null) {
            entity.setDescricao(obraInDTO.getDescricao());
        }
        if (obraInDTO.getDataPublicacao() != null) {
            entity.setDataPublicacao(obraInDTO.getDataPublicacao());
        }
        if (obraInDTO.getDataExposicao() != null) {
            entity.setDataExposicao(obraInDTO.getDataExposicao());
        }
        repository.save(entity);
        return mapper.paraDTO(entity);
    }

    public ObraOutDTO buscarObraPorId(long id) {
        ObraEntity entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado"));
        ObraOutDTO obraOutDTO = mapper.paraDTO(entity);
        return obraOutDTO;
    }

    public ObraEntity deletarObraPorId(long id) {

        ObraEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Id não encontrado"));

        entity.getAutores().clear();

        repository.save(entity);

        repository.delete(entity);

        return entity;
    }

    public void validarDatas(ObraInDTO obraInDTO) {

        if (obraInDTO.getDataExposicao() == null && (obraInDTO.getDataPublicacao() == null)) {
            throw new IllegalArgumentException("Data de exposição ou publicacão devera ser preenchida");
        }
    }
}
