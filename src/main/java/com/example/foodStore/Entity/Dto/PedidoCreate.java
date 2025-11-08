package com.example.foodStore.Entity.Dto;

import com.example.foodStore.Entity.Enums.Estado;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Builder
public class PedidoCreate {

    LocalDate fecha;
    Estado estado;
    Long usuarioId;
    List<DetallePedidoCreate> detallesPedido;

}
