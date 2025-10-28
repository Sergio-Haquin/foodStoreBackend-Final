package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioDto;
import com.example.foodStore.Entity.Mapper.UsuarioMapper;
import com.example.foodStore.Entity.Usuario;
import com.example.foodStore.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Usuario u = usuarioRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("No se encontro el usuario"));
        u.setCelular(celular);
        usuarioRepository.save(u);
    }

    @Override
    public void cambiarContrasena(String email, String contrasena) {
        Usuario u = usuarioRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("No se encontro el usuario"));
        u.setContrasena(contrasena);
        usuarioRepository.save(authService.register(u));
    }

    @Override
    public void delete(String email) {
        Usuario u = usuarioRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("No se encontro el usuario"));
        usuarioRepository.delete(u);
    }
}
