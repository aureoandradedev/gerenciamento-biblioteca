package com.biblioteca.gerenciamento.usuario.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioInDTO {
    private String nome;
    private String senha;
    private String email;
}
