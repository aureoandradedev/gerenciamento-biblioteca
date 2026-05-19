package com.biblioteca.gerenciamento.autor.entity;


import com.biblioteca.gerenciamento.obra.entity.ObraEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autor")
@Builder
public class AutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @Email
    @Valid
    @Column (name = "email", unique = true)
    private String email;

    @Valid
    @NotNull
    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @NotBlank
    @Column(name = "pais_origem", nullable = false)
    private String paisOrigem;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @ManyToMany(mappedBy = "autores")
    private List<ObraEntity> obras;

}
