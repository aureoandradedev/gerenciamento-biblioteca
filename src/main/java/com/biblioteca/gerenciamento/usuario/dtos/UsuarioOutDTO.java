package com.biblioteca.gerenciamento.usuario.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioOutDTO {
    private Long id;
    private String nome;
    private String email;
    private String role;
}
