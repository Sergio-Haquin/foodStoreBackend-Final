package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.ProductoCreate;
import com.example.foodStore.Entity.Dto.ProductoDto;

import java.util.List;

public interface ProductoService {

   // Usado para CREAR un nuevo producto. (La edición se hace con editPrice/editCategory)
   ProductoDto save(ProductoCreate productoCreate);

   // Métodos GET
   ProductoDto findByName(String nombre);
   List<ProductoDto> findByCategory(Long idCategoria);
   List<ProductoDto> findAll();

   // =============================================================
   // MÉTODOS CRUD - EDICIÓN Y ELIMINACIÓN
   // =============================================================
   void editPrice(Long id, double precio);
   void editCategory(Long id, Long idCategoria);
   void delete(Long id);
}