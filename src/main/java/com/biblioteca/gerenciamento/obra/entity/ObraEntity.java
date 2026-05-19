package com.biblioteca.gerenciamento.obra.entity;

import com.biblioteca.gerenciamento.autor.entity.AutorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "obra")
@Builder

public class ObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column (name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Column (name = "descricao", nullable = false, length = 240)
    private String descricao;


    @Column (name = "data_publicacao")
    private LocalDateTime dataPublicacao;

    @Column (name = "data_exposicao")
    private LocalDateTime dataExposicao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "obra_autor", joinColumns = @JoinColumn(name = "obra_id"), inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<AutorEntity> autores;
}
