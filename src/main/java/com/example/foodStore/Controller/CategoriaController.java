package com.example.foodStore.Controller;

import com.example.foodStore.Entity.Dto.CategoriaCreate;
import com.example.foodStore.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria")
public class CategoriaController {

   @Autowired
   CategoriaService categoriaService;

   @PostMapping("/guardar")
   public ResponseEntity<?> save(@RequestBody CategoriaCreate categoriaCreate) {
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

   @PutMapping("/editarnombre/{id}/{newNombre}")
   public ResponseEntity<?> editName(@PathVariable Long id, @PathVariable String newNombre) {
      try {
         categoriaService.editName(id, newNombre);
         return ResponseEntity.ok("Nombre de categoria editado con éxito.");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @PutMapping("/editardescripcion/{id}/{descripcion}")
   public ResponseEntity<?> editDescription(@PathVariable Long id, @PathVariable String descripcion){
      try {
         categoriaService.editDescription(id, descripcion);
         return ResponseEntity.ok("Descripción de la categoría modificada con éxito.");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @DeleteMapping("/eliminar/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
      try {
         categoriaService.delete(id);
         return ResponseEntity.ok("Categoria Eliminada con éxito.");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

}