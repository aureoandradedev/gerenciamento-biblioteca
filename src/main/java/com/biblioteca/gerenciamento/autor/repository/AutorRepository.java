package com.biblioteca.gerenciamento.autor.repository;

import com.biblioteca.gerenciamento.autor.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository <AutorEntity, Long> {

}
