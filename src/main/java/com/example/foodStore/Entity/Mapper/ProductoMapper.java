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

   public Producto toEntity(ProductoCreate pc){
      Producto producto = new Producto();
      producto.setNombre(pc.getNombre());
      producto.setSrc(pc.getSrc());
      producto.setPrecio(pc.getPrecio());
      producto.setStock(pc.getStock());
      Long idCategoria = pc.getIdCategoria();
      Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + idCategoria));
      producto.setCategoria(categoria);
      return producto;
   }

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

   public void updateEntityFromCreateDto(ProductoCreate pc, Producto producto) {
      if (pc.getNombre() != null) producto.setNombre(pc.getNombre());
      if (pc.getSrc() != null) producto.setSrc(pc.getSrc());
      if (pc.getStock() != null) producto.setStock(pc.getStock());
      producto.setPrecio(pc.getPrecio());
      if (pc.getIdCategoria() != null) {
         Categoria categoria = categoriaRepository.findById(pc.getIdCategoria())
                 .orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + pc.getIdCategoria()));
         producto.setCategoria(categoria);
      }
   }
}