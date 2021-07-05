package com.grupo.commerce.repository;

import com.grupo.commerce.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByCliente(String cliente);
}
