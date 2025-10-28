package com.example.foodStore.Service;

import com.example.foodStore.Entity.Categoria;
import com.example.foodStore.Entity.Dto.ProductoCreate;
import com.example.foodStore.Entity.Dto.ProductoDto;
import com.example.foodStore.Entity.Mapper.ProductoMapper;
import com.example.foodStore.Entity.Producto;
import com.example.foodStore.Repository.CategoriaRepository;
import com.example.foodStore.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProductoMapper productoMapper;

    @Override
    public ProductoDto save(ProductoCreate productoCreate) {
        Producto producto = productoMapper.toEntity(productoCreate);
        if (productoRepository.existsByNombre(producto.getNombre())) {
            throw new RuntimeException("El producto ya esta registrado");
        }
        producto = productoRepository.save(producto);
        return productoMapper.toDto(producto);
    }

    @Override
    public ProductoDto findByName(String nombre) {
        Producto p = productoRepository.findByName(nombre).orElseThrow(() -> new NullPointerException("Producto no encontrado"));
        return productoMapper.toDto(p);
    }

    @Override
    public List<ProductoDto> findByCategory(Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + idCategoria));
        return productoRepository.findAll().stream().filter(p -> p.getCategoria() == categoria).map(productoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void editPrice(String nombre, double precio) {
        Producto p = productoRepository.findByName(nombre).orElseThrow(() -> new NullPointerException("Producto no encontrado"));
        p.setPrecio(precio);
        productoRepository.save(p);
    }

    @Override
    public void editCategory(String nombre, Long idCategoria) {
        Producto p = productoRepository.findByName(nombre).orElseThrow(() -> new NullPointerException("Producto no encontrado"));
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new NullPointerException("No se encontro la categoria con el id " + idCategoria));
        p.setCategoria(categoria);
        productoRepository.save(p);
    }

    @Override
    public void delete(String nombre) {
        Producto p = productoRepository.findByName(nombre).orElseThrow(() -> new NullPointerException("Producto no encontrado"));
        productoRepository.delete(p);
    }
}
