package com.example.sistema_vendas.repository;

import com.example.sistema_vendas.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
