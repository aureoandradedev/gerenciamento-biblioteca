package com.biblioteca.gerenciamento.autor.service;


import com.biblioteca.gerenciamento.autor.dtos.AutorInDTO;
import com.biblioteca.gerenciamento.autor.dtos.AutorOutDTO;
import com.biblioteca.gerenciamento.autor.entity.AutorEntity;
import com.biblioteca.gerenciamento.autor.mapper.AutorMapper;
import com.biblioteca.gerenciamento.autor.repository.AutorRepository;
import com.biblioteca.gerenciamento.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class AutorService {
    private final AutorMapper mapper;
    private final AutorRepository repository;

    public AutorOutDTO criarAutor(AutorInDTO autorInDTO) {
        AutorEntity entity = mapper.paraEntity(autorInDTO);
        validarCpfBrasileiro(autorInDTO);
        repository.save(entity);
        AutorOutDTO autorOutDTO = mapper.paraDTO(entity);
        return autorOutDTO;
    }

    public AutorOutDTO atualizarDadosAutor(AutorInDTO autorInDTO, Long id) {
        AutorEntity entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
        if (autorInDTO.getNome() != null) {
            entity.setNome(autorInDTO.getNome());
        }
        if (autorInDTO.getCpf() != null) {
            entity.setCpf(autorInDTO.getCpf());
        }
        if (autorInDTO.getEmail() != null) {
            entity.setEmail(autorInDTO.getEmail());
        }
        if (autorInDTO.getDataNascimento() != null) {
            entity.setDataNascimento(autorInDTO.getDataNascimento());
        }
        if (autorInDTO.getPaisOrigem() != null) {
            entity.setPaisOrigem(autorInDTO.getPaisOrigem());
        }
        if (autorInDTO.getSexo() != null) {
            entity.setSexo(autorInDTO.getSexo());
        }
        repository.save(entity);
        return mapper.paraDTO(entity);
    }

    public AutorEntity buscarAutorPorId(Long id) {

        AutorEntity entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado"));
        return entity;
    }

    public AutorEntity deletarAutorPorId(long id) {

        AutorEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Id não encontrado"));

        entity.getObras().forEach(
                obra -> obra.getAutores().remove(entity)
        );

        repository.delete(entity);

        return entity;
    }


    public void validarCpfBrasileiro(AutorInDTO autorInDTO) {

        if (
                autorInDTO.getPaisOrigem() != null
                        &&
                        autorInDTO.getPaisOrigem().equalsIgnoreCase("Brasil")
                        &&
                        (
                                autorInDTO.getCpf() == null
                                        || autorInDTO.getCpf().isBlank()
                        )
        ) {

            throw new IllegalArgumentException(
                    "CPF é obrigatório para autores brasileiros"
            );
        }
    }
}
