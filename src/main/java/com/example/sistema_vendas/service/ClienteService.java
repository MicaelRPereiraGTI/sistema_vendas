package com.example.sistema_vendas.service;

import com.example.sistema_vendas.domain.Cliente;
import com.example.sistema_vendas.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar (Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public boolean deletar(Long id){
        return clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return true;
        }).orElse(false);
    }

    public Optional<Cliente> atualizar(Long id, Cliente novoCliente) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setCodigo(novoCliente.getCodigo());
            cliente.setNome(novoCliente.getNome());
            cliente.setEndereco(novoCliente.getEndereco());
            cliente.setTelefone(novoCliente.getTelefone());
            return clienteRepository.save(cliente);
        });
    }

}
