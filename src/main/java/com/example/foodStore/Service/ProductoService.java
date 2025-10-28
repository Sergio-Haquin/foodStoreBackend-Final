package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.ProductoCreate;
import com.example.foodStore.Entity.Dto.ProductoDto;

import java.util.List;

public interface ProductoService {

    ProductoDto save(ProductoCreate productoCreate);
    ProductoDto findByName(String nombre);
    List<ProductoDto> findByCategory(Long idCategoria);
    void editPrice(String nombre, double precio);
    void editCategory(String nombre, Long idCategoria);
    void delete(String nombre);

}
