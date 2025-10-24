package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;

public interface UsuarioService {

    UsuarioDto save(UsuarioCreate usuarioCreate);
    void addCelular(String email, Long celular);
    void cambiarContrasena(String email, String contrasena);
    void delete(String email);

}
