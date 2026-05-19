package com.biblioteca.gerenciamento.usuario.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginInDTO {

    private String email;
    private String senha;

}
