package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;
import com.example.foodStore.Entity.Mapper.UsuarioMapper;
import com.example.foodStore.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Override
    public void AuthUsuario() {
        
    }

    @Override
    public UsuarioDto save(UsuarioCreate usuarioCreate) {
        Long
        return null;
    }

    @Override
    public UsuarioDto findById(Long id) {
        return null;
    }
}
