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

   @PostMapping("/guardar")
   public ResponseEntity<?> save(@RequestBody ProductoCreate productoCreate) {
      try {
         return ResponseEntity.ok(productoService.save(productoCreate));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @GetMapping("/traeruno/{nombre}")
   public ResponseEntity<?> findByName(@PathVariable String nombre) {
      try {
         return ResponseEntity.ok(productoService.findByName(nombre));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @GetMapping("/traergrupo/{idCategoria}")
   public ResponseEntity<?> findByCategory(@PathVariable Long idCategoria) {
      try {
         return ResponseEntity.ok(productoService.findByCategory(idCategoria));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @GetMapping("/traertodos")
   public ResponseEntity<?> findAll() {
      try {
         return ResponseEntity.ok(productoService.findAll());
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @PutMapping("/editarprecio/{id}/{precio}")
   public ResponseEntity<?> editPrice(@PathVariable Long id, @PathVariable double precio) {
      try {
         productoService.editPrice(id, precio);
         return ResponseEntity.ok("Precio modificado con exito");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @PutMapping("/editarcategoria/{id}/{idCategoria}")
   public ResponseEntity<?> editCategory(@PathVariable Long id, @PathVariable Long idCategoria) {
      try {
         productoService.editCategory(id, idCategoria);
         return ResponseEntity.ok("Categoria modificada con exito");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @DeleteMapping("/eliminar/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
      try {
         productoService.delete(id);
         return ResponseEntity.ok("El producto a sido eliminado con exito");
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }
   @GetMapping("/traerid/{id}")
   public ResponseEntity<?> findById(@PathVariable Long id) {
      try {
         return ResponseEntity.ok(productoService.findById(id));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }
}