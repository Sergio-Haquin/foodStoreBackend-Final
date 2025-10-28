package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.CategoriaCreate;
import com.example.foodStore.Entity.Dto.CategoriaDto;

import java.util.List;

public interface CategoriaService {

    CategoriaDto save(CategoriaCreate categoriaCreate);
    CategoriaDto find(String nombre);
    List<CategoriaDto> findAll();
    void editName(String nombre, String newNombre);
    void editDescription(String nombre, String descripcion);
    void delete(String nombre);

}
