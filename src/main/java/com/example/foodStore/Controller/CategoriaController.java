package com.example.foodStore.Controller;

import com.example.foodStore.Entity.Dto.CategoriaCreate;
import com.example.foodStore.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/guardar")
    public ResponseEntity<?> save(CategoriaCreate categoriaCreate) {
        try {
            return ResponseEntity.ok(categoriaService.save(categoriaCreate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/traer/{nombre}")
    public ResponseEntity<?> find(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(categoriaService.find(nombre));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/traertodos")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(categoriaService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editarnombre/{nombre}/{newNombre}")
    public ResponseEntity<?> editName(@PathVariable String nombre, @PathVariable String newNombre) {
        try {
            categoriaService.editName(nombre,newNombre);
            return ResponseEntity.ok("Nombre de categoria esditado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editardescripcion/{nombre}/{descripcion}")
    public ResponseEntity<?> editDescription(@PathVariable String nombre, @PathVariable String descripcion){
        try {
            categoriaService.editDescription(nombre, descripcion);
            return ResponseEntity.ok("Descripcion de la categoria agregada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{nombre}")
    public ResponseEntity<?> delete(@PathVariable String nombre) {
        try {
            categoriaService.delete(nombre);
            return ResponseEntity.ok("Categoria Eliminada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
