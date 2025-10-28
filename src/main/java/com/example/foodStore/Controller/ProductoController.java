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

    @PutMapping("/editarprecio/{nombre}/{precio}")
    public ResponseEntity<?> editPrice(@PathVariable String nombre, @PathVariable double precio) {
        try {
            productoService.editPrice(nombre,precio);
            return ResponseEntity.ok("Precio modificado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editarcategoria/{nombre}/{idCategoria}")
    public ResponseEntity<?> editCategory(@PathVariable String nombre, @PathVariable Long idCategoria) {
        try {
            productoService.editCategory(nombre,idCategoria);
            return ResponseEntity.ok("Categoria modificada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{nombre}")
    public ResponseEntity<?> delete(@PathVariable String nombre) {
        try {
            productoService.delete(nombre);
            return ResponseEntity.ok("El producto a sido eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
