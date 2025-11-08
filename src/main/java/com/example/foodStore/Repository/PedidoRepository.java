package com.example.foodStore.Repository;

import com.example.foodStore.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByUser(Long idUser);
    List<Pedido> findAllByUser(Long idUser);

}
