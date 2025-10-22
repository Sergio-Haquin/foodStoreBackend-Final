package com.example.foodStore.Entity.Mapper;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;
import com.example.foodStore.Entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioCreate uc) {
        Usuario usuario = new Usuario();
        usuario.setNombre(uc.getNombre());
        usuario.setApellido(uc.getApellido());
        usuario.setEmail(uc.getEnail());
        usuario.setCelular(uc.getCelular());
        usuario.setContrasena(uc.getContrasena());
        usuario.setRol(uc.getRol());
        return usuario;
    }

    public UsuarioDto toDto(Usuario u) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNombre(u.getNombre());
        usuarioDto.setApellido(u.getApellido());
        usuarioDto.setEmail(u.getEmail());
        return usuarioDto;
    }

}
