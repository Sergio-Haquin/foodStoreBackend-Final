package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.ProductoCreate;
import com.example.foodStore.Entity.Dto.ProductoDto;

import java.util.List;

public interface ProductoService {

   ProductoDto save(ProductoCreate productoCreate);
   ProductoDto findByName(String nombre);
   List<ProductoDto> findByCategory(Long idCategoria);
   List<ProductoDto> findAll();
   void editPrice(Long id, double precio);
   void editCategory(Long id, Long idCategoria);
   void delete(Long id);
}