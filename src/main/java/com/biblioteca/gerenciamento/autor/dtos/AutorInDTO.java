package com.biblioteca.gerenciamento.autor.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AutorInDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String sexo;

    @NotBlank
    @Email
    @Size (max = 240)
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataNascimento;

    @NotBlank
    private String paisOrigem;

    private String cpf;

}
