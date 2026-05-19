package com.biblioteca.gerenciamento.obra.repository;

import com.biblioteca.gerenciamento.obra.entity.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository <ObraEntity, Long> {
   ;
}
