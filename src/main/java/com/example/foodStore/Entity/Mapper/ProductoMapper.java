package com.example.foodStore.Entity.Mapper;

import com.example.foodStore.Entity.Categoria;
import com.example.foodStore.Entity.Dto.ProductoCreate;
import com.example.foodStore.Entity.Dto.ProductoDto;
import com.example.foodStore.Entity.Producto;
import com.example.foodStore.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

   @Autowired
   CategoriaRepository categoriaRepository;

   @Autowired
   CategoriaMapper categoriaMapper;

   public ProductoMapper(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
      this.categoriaRepository = categoriaRepository;
      this.categoriaMapper = categoriaMapper;
   }

   // Método para mapear DTO de creación a Entidad
   public Producto toEntity(ProductoCreate pc){
      Producto producto = new Producto();
      // Nota: Asumimos que ProductoCreate ya tiene el campo 'stock'
      // 1. Asignar propiedades directas
      producto.setNombre(pc.getNombre());
      producto.setSrc(pc.getSrc());
      producto.setPrecio(pc.getPrecio());

      // 🚨 CORRECCIÓN CRÍTICA: Asignar el valor del stock desde el DTO
      producto.setStock(pc.getStock());

      // 2. Asignar Categoría (búsqueda en BD)
      Long idCategoria = pc.getIdCategoria();
      Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + idCategoria));
      producto.setCategoria(categoria);

      return producto;
   }

   // Método para mapear Entidad a DTO (Usado en findAll)
   public ProductoDto toDto(Producto p){
      ProductoDto productoDto = new ProductoDto();

      productoDto.setId(p.getId());
      productoDto.setNombre(p.getNombre());
      productoDto.setSrc(p.getSrc());
      productoDto.setPrecio(p.getPrecio());
      productoDto.setCategoria(categoriaMapper.toDto(p.getCategoria()));
      productoDto.setStock(p.getStock());

      return productoDto;
   }

   // Método de actualización (añadimos soporte para actualizar Stock)
   public void updateEntityFromCreateDto(ProductoCreate pc, Producto producto) {
      if (pc.getNombre() != null) producto.setNombre(pc.getNombre());
      if (pc.getSrc() != null) producto.setSrc(pc.getSrc());

      // 🚨 MEJORA: Incluir stock en la actualización si es diferente de null (el DTO de edición debe incluirlo)
      if (pc.getStock() != null) producto.setStock(pc.getStock());

      producto.setPrecio(pc.getPrecio());
      if (pc.getIdCategoria() != null) {
         Categoria categoria = categoriaRepository.findById(pc.getIdCategoria())
                 .orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + pc.getIdCategoria()));
         producto.setCategoria(categoria);
      }
   }
}