package com.example.foodStore.Entity.Mapper;

import com.example.foodStore.Entity.DetallePedido;
import com.example.foodStore.Entity.Dto.DetallePedidoCreate;
import com.example.foodStore.Entity.Dto.DetallePedidoDto;
import com.example.foodStore.Entity.Producto;
import com.example.foodStore.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ProductoMapper productoMapper;

    public DetallePedido toEntity(DetallePedidoCreate dpc) {
        DetallePedido detallePedido = new DetallePedido();
        Long idProducto = dpc.getIdProducto();
        Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new NullPointerException("Producto no encontrado con el id: " + idProducto));
        detallePedido.setProducto(producto);
        detallePedido.setCantidad(dpc.getCantidad());
        detallePedido.setSubtotal(producto.getPrecio() * dpc.getCantidad());
        return detallePedido;
    }

    public DetallePedidoDto toDto(DetallePedido dp) {
        DetallePedidoDto detallePedidoDto = new DetallePedidoDto();
        detallePedidoDto.setId(dp.getId());
        detallePedidoDto.setProductoDto(productoMapper.toDto(dp.getProducto()));
        detallePedidoDto.setCantidad(dp.getCantidad());
        detallePedidoDto.setSubtotal(dp.getSubtotal());
        return detallePedidoDto;
    }

}
