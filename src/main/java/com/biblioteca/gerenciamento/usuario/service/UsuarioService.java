package com.biblioteca.gerenciamento.usuario.service;

import com.biblioteca.gerenciamento.exceptions.ConflictException;
import com.biblioteca.gerenciamento.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.security.JwtUtil;
import com.biblioteca.gerenciamento.usuario.dtos.LoginInDTO;
import com.biblioteca.gerenciamento.usuario.dtos.LoginOutDTO;
import com.biblioteca.gerenciamento.usuario.dtos.UsuarioInDTO;
import com.biblioteca.gerenciamento.usuario.dtos.UsuarioOutDTO;
import com.biblioteca.gerenciamento.usuario.entity.Usuario;
import com.biblioteca.gerenciamento.usuario.mapper.UsuarioMapper;
import com.biblioteca.gerenciamento.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    // =========================
    // SALVAR USUÁRIO
    // =========================

    public UsuarioOutDTO salvaUsuario(
            UsuarioInDTO usuarioInDTO
    ) {

        emailExiste(usuarioInDTO.getEmail());

        Usuario usuario =
                mapper.paraEntity(usuarioInDTO);

        usuario.setSenha(
                passwordEncoder.encode(
                        usuarioInDTO.getSenha()
                )
        );

        usuario.setRole("USER");

        Usuario entitySalva =
                usuarioRepository.save(usuario);

        return mapper.paraDTO(entitySalva);
    }

    // =========================
    // VALIDAR EMAIL
    // =========================

    public void emailExiste(String email) {

        try {

            boolean existe =
                    verificaEmailExistente(email);

            if (existe) {

                throw new ConflictException(
                        "Email já cadastrado: " + email
                );
            }

        } catch (ConflictException e) {

            throw new ConflictException(
                    "Email já cadastrado",
                    e.getCause()
            );
        }
    }

    public boolean verificaEmailExistente(
            String email
    ) {

        return usuarioRepository.existsByEmail(email);
    }

    // =========================
    // LOGIN
    // =========================

    public LoginOutDTO loginUsuario(
            LoginInDTO dto
    ) {

        Usuario usuario =
                usuarioRepository.findByEmail(dto.getEmail())

                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Email não encontrado"
                                )
                        );

        if (!passwordEncoder.matches(
                dto.getSenha(),
                usuario.getSenha()
        )) {

            throw new ResourceNotFoundException(
                    "Credenciais inválidas"
            );
        }

        // access token
        String accessToken =
                jwtUtil.generateToken(
                        usuario.getEmail()
                );

        // refresh token
        String refreshToken =
                jwtUtil.generateRefreshToken(
                        usuario.getEmail()
                );

        return LoginOutDTO.builder()

                .accessToken(accessToken)

                .refreshToken(refreshToken)

                .build();
    }
}