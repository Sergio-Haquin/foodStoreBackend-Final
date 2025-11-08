package com.example.foodStore.Service;

import com.example.foodStore.Entity.DetallePedido;
import com.example.foodStore.Entity.Dto.DetallePedidoCreate;
import com.example.foodStore.Entity.Dto.PedidoCreate;
import com.example.foodStore.Entity.Dto.PedidoDto;
import com.example.foodStore.Entity.Enums.Estado;
import com.example.foodStore.Entity.Mapper.DetallePedidoMapper;
import com.example.foodStore.Entity.Mapper.PedidoMapper;
import com.example.foodStore.Entity.Pedido;
import com.example.foodStore.Entity.Producto;
import com.example.foodStore.Entity.Usuario;
import com.example.foodStore.Repository.PedidoRepository;
import com.example.foodStore.Repository.ProductoRepository;
import com.example.foodStore.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImp implements PedidoService{

   @Autowired
   PedidoRepository pedidoRepository;

   @Autowired
   UsuarioRepository usuarioRepository;

   @Autowired
   ProductoRepository productoRepository;

   @Autowired
   PedidoMapper pedidoMapper;

   @Autowired
   DetallePedidoMapper detallePedidoMapper;

   @Override
   @Transactional
   public PedidoDto save(PedidoCreate pedidoCreate) {
      Pedido pedido = pedidoMapper.toEntity(pedidoCreate);
      double total = 0.0;

      for (DetallePedido dp : pedido.getDetallesPedido()) {
         Optional<Producto> productoOpt = productoRepository.findById(dp.getProducto().getId());
         if (productoOpt.isEmpty()) {
            throw new RuntimeException("Error al procesar el pedido: Producto con ID " + dp.getProducto().getId() + " no encontrado.");
         }

         Producto producto = productoOpt.get();
         int cantidadSolicitada = dp.getCantidad();

         if (producto.getStock() < cantidadSolicitada) {
            throw new RuntimeException("Stock insuficiente para: " + producto.getNombre() +
                    ". Disponible: " + producto.getStock() + ", Solicitado: " + cantidadSolicitada);
         }

         dp.setPedido(pedido);
         total += dp.getSubtotal();
      }

      pedido.setTotal(total);
      Pedido pedidoGuardado = pedidoRepository.save(pedido);

      for (DetallePedido dp : pedidoGuardado.getDetallesPedido()) {
         Producto producto = productoRepository.findById(dp.getProducto().getId()).get();
         producto.setStock(producto.getStock() - dp.getCantidad());
         productoRepository.save(producto);
      }

      return pedidoMapper.toDto(pedidoGuardado);
   }

   @Override
   public PedidoDto findByUser(Long idUser) {
      Usuario u = usuarioRepository.findById(idUser).orElseThrow(() -> new NullPointerException("No se encontro el usuario"));
      Pedido pedido = pedidoRepository.findByUsuario(u).orElseThrow(() -> new NullPointerException("No se encontro el pedido con el ususario"));
      return pedidoMapper.toDto(pedido);
   }

   @Override
   public List<PedidoDto> findAllByUser(Long idUser) {
      Usuario u = usuarioRepository.findById(idUser).orElseThrow(() -> new NullPointerException("No se encontro el usuario"));
      List<Pedido> pedidos = pedidoRepository.findAllByUsuario(u);
      if(pedidos.isEmpty()){
         throw new RuntimeException("No hay pedidos registrados de ese usuario");
      }
      return pedidos.stream().map(pedidoMapper::toDto).toList();
   }

   @Override
   public List<PedidoDto> findAll() {
      List<Pedido> pedidos = pedidoRepository.findAll();
      if(pedidos.isEmpty()){
         throw new RuntimeException("No hay pedidos registrados");
      }
      return pedidos.stream().map(pedidoMapper::toDto).toList();
   }

   @Override
   @Transactional
   public void agregarDetalle(Long id, DetallePedidoCreate detallePedidoCreate) {
      Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NullPointerException("El pedido no fue encontrado"));
      if(detallePedidoCreate == null){
         throw  new RuntimeException("No hay informacion de los detalles");
      }

      DetallePedido dp = detallePedidoMapper.toEntity(detallePedidoCreate);
      pedido.getDetallesPedido().add(dp);

      dp.setPedido(pedido);
      recalcularTotal(pedido);

      pedidoRepository.save(pedido);

   }

   @Override
   @Transactional
   public void editEstado(Long id, Estado estado) {
      Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NullPointerException("El pedido no fue encontrado"));
      if(pedido.getEstado() == estado){
         throw new RuntimeException("El estado del pedido ya se encuentra en ese estado");
      }
      if (estado == Estado.CANCELADO) {

         for (DetallePedido dp : pedido.getDetallesPedido()) {
            Producto producto = productoRepository.findById(dp.getProducto().getId()).get();
            producto.setStock(producto.getStock() + dp.getCantidad());
            productoRepository.save(producto);
         }
      }

      pedido.setEstado(estado);
      pedidoRepository.save(pedido);
   }

   @Override
   @Transactional
   public void eliminarDetalle(Long id, Long idDetallePedido) {
      Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NullPointerException("El pedido no fue encontrado"));
      DetallePedido detallePedido = pedido.getDetallesPedido().stream().filter(dp -> dp.getId().equals(idDetallePedido)).findFirst().orElseThrow(() -> new NullPointerException("No se encontro el detalle con el id: "+ idDetallePedido));
      if (pedido.getEstado() != Estado.PENDIENTE && pedido.getEstado() != Estado.CANCELADO) {
         Producto producto = productoRepository.findById(detallePedido.getProducto().getId()).get();
         producto.setStock(producto.getStock() + detallePedido.getCantidad());
         productoRepository.save(producto);
      }

      pedido.getDetallesPedido().remove(detallePedido);
      recalcularTotal(pedido);
      pedidoRepository.save(pedido);
   }

   @Override
   public void delete(Long id) {
      Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NullPointerException("El pedido no fue encontrado"));
      if (pedido.getEstado() != Estado.CANCELADO && pedido.getEstado() != Estado.TERMINADO) {
         for (DetallePedido dp : pedido.getDetallesPedido()) {
            Producto producto = productoRepository.findById(dp.getProducto().getId()).get();
            producto.setStock(producto.getStock() + dp.getCantidad());
            productoRepository.save(producto);
         }
      }

      pedidoRepository.delete(pedido);
   }

   private void recalcularTotal(Pedido pedido) {
      double nuevoTotal = pedido.getDetallesPedido().stream()
              .mapToDouble(DetallePedido::getSubtotal)
              .sum();
      pedido.setTotal(nuevoTotal);
   }
}