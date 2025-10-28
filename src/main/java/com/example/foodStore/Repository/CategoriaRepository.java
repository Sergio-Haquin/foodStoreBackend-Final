package com.example.foodStore.Repository;

import com.example.foodStore.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByName(String nombre);
    boolean existsByNombre(String nombre);
}
