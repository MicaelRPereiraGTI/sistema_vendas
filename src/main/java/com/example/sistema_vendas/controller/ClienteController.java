package com.example.sistema_vendas.controller;


import com.example.sistema_vendas.domain.Cliente;
import com.example.sistema_vendas.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController (ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
        Cliente salvo = clienteService.salvar(cliente);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos(){
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (clienteService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar (@PathVariable Long id, @RequestBody Cliente clienteAtualizado){
        return clienteService.atualizar(id, clienteAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
