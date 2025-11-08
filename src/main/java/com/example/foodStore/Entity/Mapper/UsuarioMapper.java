package com.example.foodStore.Entity.Mapper;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;
import com.example.foodStore.Entity.Enums.Rol;
import com.example.foodStore.Entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioCreate uc) {
        Usuario usuario = new Usuario();
        usuario.setNombre(uc.getNombre());
        usuario.setApellido(uc.getApellido());
        usuario.setEmail(uc.getEmail());
        usuario.setContrasena(uc.getContrasena());
        usuario.setRol(Rol.USUARIO);
        return usuario;
    }

    public UsuarioDto toDto(Usuario u) {
        UsuarioDto usuarioDto = new UsuarioDto();
       usuarioDto.setIdUsuario(u.getId());
        usuarioDto.setNombre(u.getNombre());
        usuarioDto.setApellido(u.getApellido());
        usuarioDto.setEmail(u.getEmail());
        usuarioDto.setRol(u.getRol());
        return usuarioDto;
    }

}
