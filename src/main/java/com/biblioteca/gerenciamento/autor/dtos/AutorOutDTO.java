package com.biblioteca.gerenciamento.autor.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AutorOutDTO {
    private Long id;
    @NotBlank

    private String nome;

    @NotBlank
    private String sexo;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataNascimento;

    @NotBlank
    private String paisOrigem;

    private String cpf;

}
