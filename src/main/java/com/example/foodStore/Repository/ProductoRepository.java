package com.example.foodStore.Repository;

import com.example.foodStore.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
