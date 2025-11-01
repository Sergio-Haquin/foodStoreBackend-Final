package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.CategoriaCreate;
import com.example.foodStore.Entity.Dto.CategoriaDto;
import java.util.List;

public interface CategoriaService {

   CategoriaDto save(CategoriaCreate categoriaCreate);

   CategoriaDto find(String nombre);

   List<CategoriaDto> findAll();

   void editName(Long id, String newNombre);
   void editDescription(Long id, String descripcion);

   void delete(Long id);

}