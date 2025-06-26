package com.example.sistema_vendas.repository;


import com.example.sistema_vendas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
