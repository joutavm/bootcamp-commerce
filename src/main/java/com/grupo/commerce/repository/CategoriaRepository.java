package com.grupo.commerce.repository;

import com.grupo.commerce.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String nomeCategoria);
}
