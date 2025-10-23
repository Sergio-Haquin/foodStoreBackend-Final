package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.UsuarioDto;
import com.example.foodStore.Entity.Mapper.UsuarioMapper;
import com.example.foodStore.Entity.Usuario;
import com.example.foodStore.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    public Usuario register(Usuario usuario){
        usuario.setContrasena(Usuario.hash(usuario.getContrasena()));
        return usuario;
    }

    public UsuarioDto login(String email, String contrasena){
        Optional<Usuario> u = usuarioRepository.findByEmail(email);
        if(u.isEmpty()){
            throw new RuntimeException("Usuario no registrado");
        }
        Usuario usuario = u.get();
        String contrasenaHash = Usuario.hash(contrasena);
        if(!contrasenaHash.equals(usuario.getContrasena())){
            throw new RuntimeException("Contraseña invalida");
        }
        return usuarioMapper.toDto(usuario);
    }

}
