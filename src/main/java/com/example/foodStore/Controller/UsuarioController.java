package com.example.foodStore.Controller;

import com.example.foodStore.Entity.Dto.UsuarioCreate;
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

    @GetMapping("/login/{email}/{contrasena}")
    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String contrasena) {
        try {
            return ResponseEntity.ok(authService.login(email, contrasena));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/addcelular/{id}/{celular}")
    public ResponseEntity<?> addCelular(@PathVariable Long id, @PathVariable int celular) {
        try {
            usuarioService.addCelular(id, celular);
            return ResponseEntity.ok("Celular Agregado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.ok("Usuario Eliminado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
