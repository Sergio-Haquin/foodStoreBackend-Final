package com.example.foodStore.Entity.Mapper;

import com.example.foodStore.Entity.Dto.PedidoCreate;
import com.example.foodStore.Entity.Dto.PedidoDto;
import com.example.foodStore.Entity.Pedido;
import com.example.foodStore.Entity.Usuario;
import com.example.foodStore.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    DetallePedidoMapper detallePedidoMapper;

    public Pedido toEntity(PedidoCreate pc) {
        Pedido pedido = new Pedido();
        Long idUsuario = pc.getUsuarioId();
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NullPointerException("No se encontro el usuario con el id: "+ idUsuario));
        pedido.setUsuario(usuario);
        pedido.setFecha(pc.getFecha());
        pedido.setEstado(pc.getEstado());
        pedido.setDetallesPedido(pc.getDetallesPedido().stream().map(detallePedidoMapper::toEntity).toList());
        return pedido;
    }

    public PedidoDto toDto(Pedido p) {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setId(p.getId());
        pedidoDto.setUsuarioDto(usuarioMapper.toDto(p.getUsuario()));
        pedidoDto.setFecha(p.getFecha());
        pedidoDto.setEstado(p.getEstado());
        pedidoDto.setDetallesPedido(p.getDetallesPedido().stream().map(detallePedidoMapper::toDto).toList());
        pedidoDto.setTotal(p.getTotal());
        return pedidoDto;
    }

}
