package com.example.foodStore.Controller;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
import com.example.foodStore.Entity.Dto.UsuarioLogin;
import com.example.foodStore.Service.AuthService;
import com.example.foodStore.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AuthService authService;

    @PostMapping("/guardar")
    public ResponseEntity<?> save(@RequestBody UsuarioCreate usuarioCreate){
        try {
            return ResponseEntity.ok(usuarioService.save(usuarioCreate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLogin usuarioLogin) {
        try {
            return ResponseEntity.ok(authService.login(usuarioLogin.getEmail(), usuarioLogin.getContrasena()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/addcelular/{email}/{celular}")
    public ResponseEntity<?> addCelular(@PathVariable String email, @PathVariable Long celular) {
        try {
            usuarioService.addCelular(email, celular);
            return ResponseEntity.ok("Celular Agregado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/changecontrasena/{email}/{contrasena}")
    public ResponseEntity<?> cambiarContrasena(@PathVariable String email, @PathVariable String contrasena) {
        try {
            usuarioService.cambiarContrasena(email, contrasena);
            return ResponseEntity.ok("Cambio de contraseña exitoso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{email}")
    public ResponseEntity<?> delete(@PathVariable String email) {
        try {
            usuarioService.delete(email);
            return ResponseEntity.ok("Usuario Eliminado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
