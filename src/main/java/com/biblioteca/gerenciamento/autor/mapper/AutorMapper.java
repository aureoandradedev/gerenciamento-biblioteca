package com.biblioteca.gerenciamento.autor.mapper;

import com.biblioteca.gerenciamento.autor.dtos.AutorInDTO;
import com.biblioteca.gerenciamento.autor.dtos.AutorOutDTO;
import com.biblioteca.gerenciamento.autor.entity.AutorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {
    AutorOutDTO paraDTO(AutorEntity autorEntity);
    AutorEntity paraEntity(AutorInDTO autorInDTO);
}
