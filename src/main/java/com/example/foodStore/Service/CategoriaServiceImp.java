package com.example.foodStore.Service;

import com.example.foodStore.Entity.Categoria;
import com.example.foodStore.Entity.Dto.CategoriaCreate;
import com.example.foodStore.Entity.Dto.CategoriaDto;
import com.example.foodStore.Entity.Mapper.CategoriaMapper;
import com.example.foodStore.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImp implements CategoriaService {

   @Autowired
   CategoriaRepository categoriaRepository;

   @Autowired
   CategoriaMapper categoriaMapper;

   @Override
   public CategoriaDto save(CategoriaCreate cc) {
      Categoria categoria = categoriaMapper.toEntity(cc);
      if (categoriaRepository.existsByNombre(categoria.getNombre())) {
         throw new RuntimeException("La categoria ya esta registrada");
      }
      categoria = categoriaRepository.save(categoria);
      return categoriaMapper.toDto(categoria);
   }

   @Override
   public CategoriaDto find(String nombre) {
      Categoria c = categoriaRepository.findByNombre(nombre).orElseThrow(() -> new NullPointerException("No se encontro la categoria"));
      return categoriaMapper.toDto(c);
   }

   @Override
   public List<CategoriaDto> findAll() {
      return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).toList();
   }

   @Override
   public void editName(Long id, String newNombre) {
      Categoria c = categoriaRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la categoria con ID: " + id));
      if (categoriaRepository.existsByNombre(newNombre) && !c.getNombre().equals(newNombre)) {
         throw new RuntimeException("El nuevo nombre de categoria ya esta registrado.");
      }
      c.setNombre(newNombre);
      categoriaRepository.save(c);
   }

   @Override
   public void editDescription(Long id, String descripcion) {
      Categoria c = categoriaRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la categoria con ID: " + id));
      c.setDescripcion(descripcion);
      categoriaRepository.save(c);
   }

   @Override
   public void delete(Long id) {
      Categoria c = categoriaRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro la categoria con ID: " + id));
      categoriaRepository.delete(c);
   }
}