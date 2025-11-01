package com.example.foodStore.Controller;

import com.example.foodStore.Entity.Dto.ProductoCreate;
import com.example.foodStore.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/producto")
public class ProductoController {

   @Autowired
   ProductoService productoService;

   // POST: /producto/guardar (Crear Producto)
   // Nota: Usar @RequestBody es correcto si el Frontend envía JSON, no form-urlencoded.
   @PostMapping("/guardar")
   public ResponseEntity<?> save(@RequestBody ProductoCreate productoCreate) {
      try {
         return ResponseEntity.ok(productoService.save(productoCreate));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // GET: /producto/traeruno/{nombre}
   @GetMapping("/traeruno/{nombre}")
   public ResponseEntity<?> findByName(@PathVariable String nombre) {
      try {
         return ResponseEntity.ok(productoService.findByName(nombre));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // GET: /producto/traergrupo/{idCategoria}
   @GetMapping("/traergrupo/{idCategoria}")
   public ResponseEntity<?> findByCategory(@PathVariable Long idCategoria) {
      try {
         return ResponseEntity.ok(productoService.findByCategory(idCategoria));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // GET: /producto/traertodos (Asumido que esta función es necesaria)
   @GetMapping("/traertodos")
   public ResponseEntity<?> findAll() {
      try {
         return ResponseEntity.ok(productoService.findAll());
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }


   // =============================================================
   // MÉTODOS MODIFICADOS PARA USAR ID (Long)
   // =============================================================

   // PUT: /producto/editarprecio/{id}/{precio} (Editar Precio por ID)
   @PutMapping("/editarprecio/{id}/{precio}") // <-- RUTA MODIFICADA
   public ResponseEntity<?> editPrice(@PathVariable Long id, @PathVariable double precio) { // <-- PARÁMETRO MODIFICADO
      try {
         // ASUMIMOS que productoService.editPrice acepta Long id, double precio
         productoService.editPrice(id, precio);
         return ResponseEntity.ok("Precio modificado con exito");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // PUT: /producto/editarcategoria/{id}/{idCategoria} (Editar Categoría por ID)
   @PutMapping("/editarcategoria/{id}/{idCategoria}") // <-- RUTA MODIFICADA
   public ResponseEntity<?> editCategory(@PathVariable Long id, @PathVariable Long idCategoria) { // <-- PARÁMETRO MODIFICADO
      try {
         // ASUMIMOS que productoService.editCategory acepta Long id, Long idCategoria
         productoService.editCategory(id, idCategoria);
         return ResponseEntity.ok("Categoria modificada con exito");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   // DELETE: /producto/eliminar/{id} (Eliminar por ID)
   @DeleteMapping("/eliminar/{id}") // <-- RUTA MODIFICADA
   public ResponseEntity<?> delete(@PathVariable Long id) { // <-- PARÁMETRO MODIFICADO
      try {
         // ASUMIMOS que productoService.delete acepta Long id
         productoService.delete(id);
         return ResponseEntity.ok("El producto a sido eliminado con exito");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }
}