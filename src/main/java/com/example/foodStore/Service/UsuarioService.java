package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;
import com.example.foodStore.Entity.Usuario;

public interface UsuarioService {

    public UsuarioDto save(UsuarioCreate usuarioCreate);
    public void addCelular(Long id, int celular);
    public void delete(Long id);

}
