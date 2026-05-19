package com.biblioteca.gerenciamento.usuario.mapper;

import com.biblioteca.gerenciamento.usuario.dtos.UsuarioInDTO;
import com.biblioteca.gerenciamento.usuario.dtos.UsuarioOutDTO;
import com.biblioteca.gerenciamento.usuario.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioOutDTO paraDTO(Usuario usuario);
    Usuario paraEntity(UsuarioInDTO usuarioInDTO);
}
