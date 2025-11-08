package com.example.foodStore.Controller;

import com.example.foodStore.Entity.Dto.DetallePedidoCreate;
import com.example.foodStore.Entity.Dto.PedidoCreate;
import com.example.foodStore.Entity.Enums.Estado;
import com.example.foodStore.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping("/guardar")
    public ResponseEntity<?> save(@RequestBody PedidoCreate pedidoCreate) {
        try {
            return ResponseEntity.ok(pedidoService.save(pedidoCreate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/traeruno/{idUser}")
    public ResponseEntity<?> findByUser(@PathVariable Long idUser) {
        try {
            return ResponseEntity.ok(pedidoService.findByUser(idUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/traertodosusuario/{idUser}")
    public ResponseEntity<?> findAllByUser(@PathVariable Long idUser) {
        try {
            return ResponseEntity.ok(pedidoService.findAllByUser(idUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/traertodos")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(pedidoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/agregardetalle/{idPedido}")
    public ResponseEntity<?> agregarDetalle(@PathVariable Long idPedido, @RequestBody DetallePedidoCreate detallePedidoCreate) {
        try {
            pedidoService.agregarDetalle(idPedido, detallePedidoCreate);
            return ResponseEntity.ok("Detalle agregado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editarestado/{idPedido}/{estado}")
    public ResponseEntity<?> editEstado(@PathVariable Long idPedido, @PathVariable Estado estado) {
        try {
            pedidoService.editEstado(idPedido, estado);
            return ResponseEntity.ok("Estado actualizado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminardetalle/{idPedido}/{idDetalle}")
    public ResponseEntity<?> eliminarDetalle(@PathVariable Long idPedido, @PathVariable Long idDetalle) {
        try {
            pedidoService.eliminarDetalle(idPedido,idDetalle);
            return ResponseEntity.ok("Detalle del pedido eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            pedidoService.delete(id);
            return ResponseEntity.ok("Pedido eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
