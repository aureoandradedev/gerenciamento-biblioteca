package com.biblioteca.gerenciamento.usuario.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginOutDTO {
    private String accessToken;
    private String refreshToken;
}
