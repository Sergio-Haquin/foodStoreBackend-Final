package com.example.foodStore.Entity.Mapper;

import com.example.foodStore.Entity.Categoria;
import com.example.foodStore.Entity.Dto.CategoriaCreate;
import com.example.foodStore.Entity.Dto.CategoriaDto;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

   public Categoria toEntity(CategoriaCreate cc){
      Categoria categoria = new Categoria();
      categoria.setNombre(cc.getNombre());
      categoria.setDescripcion(cc.getDescripcion());
      return categoria;
   }

   public CategoriaDto toDto(Categoria c){
      CategoriaDto categoriaDto = new CategoriaDto();
      categoriaDto.setId(c.getId());

      categoriaDto.setNombre(c.getNombre());
      categoriaDto.setDescripcion(c.getDescripcion());
      return categoriaDto;
   }
}