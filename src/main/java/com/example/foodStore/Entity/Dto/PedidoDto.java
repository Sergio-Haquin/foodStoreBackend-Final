package com.example.foodStore.Entity.Dto;

import com.example.foodStore.Entity.Enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
    Long id;
    LocalDate fecha;
    Estado estado;
    double total;
    List<DetallePedidoDto> detallesPedido;
    UsuarioDto usuarioDto;
}
