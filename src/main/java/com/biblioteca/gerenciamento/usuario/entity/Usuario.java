package com.biblioteca.gerenciamento.usuario.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "usuario")
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "nome", length = 100)
    private String nome;
    @Column (name = "email", length = 100)
    private String email;
    @Column (name = "senha")
    private String senha;
    private String role;

}
