package com.biblioteca.gerenciamento.obra.mapper;

import com.biblioteca.gerenciamento.obra.dtos.ObraInDTO;
import com.biblioteca.gerenciamento.obra.dtos.ObraOutDTO;
import com.biblioteca.gerenciamento.obra.entity.ObraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface ObraMapper {
    @Mapping(target = "autorIds",
            expression = "java(obraEntity.getAutores().stream()" +
                    ".map(autor -> autor.getId())" +
                    ".toList())")
    ObraOutDTO paraDTO(ObraEntity obraEntity);
    ObraEntity paraEntity(ObraInDTO obraInDTO);
}
