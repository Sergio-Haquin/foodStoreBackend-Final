package com.example.foodStore.Controller;

import com.example.foodStore.Entity.Dto.CategoriaCreate;
import com.example.foodStore.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController // ⬅️ CAMBIO SUGERIDO: Usar RestController para simplificar
@CrossOrigin("*")
@RequestMapping("/categoria")
public class CategoriaController {

   @Autowired
   CategoriaService categoriaService;

   // POST: /categoria/guardar (Crear, usa DTO)
   @PostMapping("/guardar")
   // 🛑 CORRECCIÓN CLAVE: Se añade @RequestBody para recibir JSON del Frontend.
   public ResponseEntity<?> save(@RequestBody CategoriaCreate categoriaCreate) {
      try {
         // El Service debería devolver el objeto Categoria (con ID) para el Frontend
         return ResponseEntity.ok(categoriaService.save(categoriaCreate));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // GET: /categoria/traer/{nombre} (Buscar por Nombre)
   @GetMapping("/traer/{nombre}")
   public ResponseEntity<?> find(@PathVariable String nombre) {
      try {
         return ResponseEntity.ok(categoriaService.find(nombre));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // GET: /categoria/traertodos (Traer todos)
   @GetMapping("/traertodos")
   public ResponseEntity<?> findAll() {
      try {
         // ⚠️ NOTA: El problema de IDs nulos está aquí, en la respuesta del Service.
         // El Controller está correcto, pero el método findAll() del Service debe
         // asegurarse de que los objetos devueltos incluyan el ID.
         return ResponseEntity.ok(categoriaService.findAll());
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // =================================================================
   // MÉTODOS MODIFICADOS PARA USAR ID (Long) EN LUGAR DE NOMBRE (String)
   // =================================================================

   // PUT: /categoria/editarnombre/{id}/{newNombre} (Editar Nombre por ID)
   @PutMapping("/editarnombre/{id}/{newNombre}")
   public ResponseEntity<?> editName(@PathVariable Long id, @PathVariable String newNombre) {
      try {
         categoriaService.editName(id, newNombre);
         return ResponseEntity.ok("Nombre de categoria editado con éxito.");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // PUT: /categoria/editardescripcion/{id}/{descripcion} (Editar Descripción por ID)
   @PutMapping("/editardescripcion/{id}/{descripcion}")
   public ResponseEntity<?> editDescription(@PathVariable Long id, @PathVariable String descripcion){
      try {
         categoriaService.editDescription(id, descripcion);
         return ResponseEntity.ok("Descripción de la categoría modificada con éxito.");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // DELETE: /categoria/eliminar/{id} (Eliminar por ID)
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