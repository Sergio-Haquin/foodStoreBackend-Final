package com.example.foodStore.Repository;

import com.example.foodStore.Entity.Pedido;
import com.example.foodStore.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByUsuario(Usuario usuario);
    List<Pedido> findAllByUsuario(Usuario usuario);

}
