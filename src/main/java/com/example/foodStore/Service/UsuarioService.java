package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;

public interface UsuarioService {

    public UsuarioDto save(UsuarioCreate usuarioCreate);
    public void addCelular(String email, Long celular);
    public void cambiarContrasena(String email, String contrasena);
    public void delete(String email);

}
