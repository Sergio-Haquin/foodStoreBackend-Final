package com.example.foodStore.Service;

import com.example.foodStore.Entity.Dto.DetallePedidoCreate;
import com.example.foodStore.Entity.Dto.PedidoCreate;
import com.example.foodStore.Entity.Dto.PedidoDto;
import com.example.foodStore.Entity.Enums.Estado;

import java.util.List;

public interface PedidoService {

    PedidoDto save(PedidoCreate pedidoCreate);
    PedidoDto findByUser(Long idUser);
    List<PedidoDto> findAllByUser(Long idUser);
    List<PedidoDto> findAll();
    void agregarDetalle(Long id, DetallePedidoCreate detallePedidoCreate);
    void editEstado(Long id, Estado estado);
    void eliminarDetalle(Long id, Long idDetallePedido);
    void delete(Long id);

}
