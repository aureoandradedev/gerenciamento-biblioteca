package com.biblioteca.gerenciamento.usuario.controller;


import com.biblioteca.gerenciamento.security.JwtUtil;
import com.biblioteca.gerenciamento.usuario.dtos.*;
import com.biblioteca.gerenciamento.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    @PostMapping ("registrar")
    public ResponseEntity <UsuarioOutDTO> criarUsuario (@RequestBody UsuarioInDTO usuarioInDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioInDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutDTO> loginUsuario(@RequestBody LoginInDTO loginInDTO){
        return ResponseEntity.ok(usuarioService.loginUsuario(loginInDTO));
    }
    @PostMapping("/refresh")
    public ResponseEntity<LoginOutDTO> refreshToken(
            @RequestBody RefreshTokenDTO dto
    ) {

        String email =
                jwtUtil.extrairEmailToken(dto.getRefreshToken());

        if (jwtUtil.isTokenExpired(dto.getRefreshToken())) {
            throw new RuntimeException("Refresh token expirado");
        }

        String newAccessToken =
                jwtUtil.generateToken(email);

        String newRefreshToken =
                jwtUtil.generateRefreshToken(email);

        return ResponseEntity.ok(
                LoginOutDTO.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(newRefreshToken)
                        .build()
        );
    }
}
