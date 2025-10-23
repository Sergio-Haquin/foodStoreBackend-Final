package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;

public interface UsuarioService {

    public UsuarioDto save(UsuarioCreate usuarioCreate);
    public void addCelular(Long id, int celular);
    public void delete(Long id);

}
