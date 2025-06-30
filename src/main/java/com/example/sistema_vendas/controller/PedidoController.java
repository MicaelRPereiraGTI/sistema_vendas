package com.example.sistema_vendas.controller;

import com.example.sistema_vendas.dto.PedidoDTO;
import com.example.sistema_vendas.service.PedidoService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO novoPedido = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarTodos() {
        List<PedidoDTO> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }
}

