package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;
import com.example.foodStore.Entity.Mapper.UsuarioMapper;
import com.example.foodStore.Entity.Usuario;
import com.example.foodStore.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    AuthService authService;

    @Override
    public UsuarioDto save(UsuarioCreate uc){
        Usuario usuario = usuarioMapper.toEntity(uc);
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo utilizado ya esta registrado");
        }
        usuario = usuarioRepository.save(authService.register(usuario));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public void addCelular(String email, Long celular) {
        Optional<Usuario> u = usuarioRepository.findByEmail(email);
        if (u.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        Usuario usuario = u.get();
        usuario.setCelular(celular);
        usuarioRepository.save(usuario);
    }

    @Override
    public void cambiarContrasena(String email, String contrasena) {
        Optional<Usuario> u = usuarioRepository.findByEmail(email);
        if (u.isEmpty()) {
            throw new RuntimeException("usuario no encontrado");
        }
        Usuario usuario = u.get();
        usuario.setContrasena(contrasena);
        usuarioRepository.save(authService.register(usuario));
    }

    @Override
    public void delete(String email) {
        Optional<Usuario> u = usuarioRepository.findByEmail(email);
        if (u.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        Usuario usuario = u.get();
        usuarioRepository.delete(usuario);
    }
}
