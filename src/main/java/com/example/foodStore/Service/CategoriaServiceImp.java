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
        Categoria c = categoriaRepository.findByEliminadoFalse().orElseThrow(() -> new NullPointerException("No se encontro la categoria"));
        return categoriaMapper.toDto(c);
    }

    @Override
    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll().stream().filter(c -> c.get).map(categoriaMapper::toDto).toList();
    }

    @Override
    public void editName(String nombre, String newNombre) {
        Categoria c = categoriaRepository.findByNombre(nombre).orElseThrow(() -> new NullPointerException("No se encontro la categoria"));
        c.setNombre(newNombre);
        categoriaRepository.save(c);
    }

    @Override
    public void editDescription(String nombre, String descripcion) {
        Categoria c = categoriaRepository.findByNombre(nombre).orElseThrow(() -> new NullPointerException("No se encontro la categoria"));
        c.setDescripcion(descripcion);
        categoriaRepository.save(c);
    }

    @Override
    public void delete(String nombre) {
        Categoria c = categoriaRepository.findByNombre(nombre).orElseThrow(() -> new NullPointerException("No se encontro la categoria"));
        categoriaRepository.delete(c);
    }
}
