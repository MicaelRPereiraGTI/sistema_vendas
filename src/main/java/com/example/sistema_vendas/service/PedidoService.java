package com.example.sistema_vendas.service;

import com.example.sistema_vendas.domain.Cliente;
import com.example.sistema_vendas.domain.ItemPedido;
import com.example.sistema_vendas.domain.Pedido;
import com.example.sistema_vendas.domain.Produto;
import com.example.sistema_vendas.dto.ItemPedidoDTO;
import com.example.sistema_vendas.dto.PedidoDTO;
import com.example.sistema_vendas.repository.ClienteRepository;
import com.example.sistema_vendas.repository.PedidoRepository;
import com.example.sistema_vendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setData(LocalDateTime.now());

        if (pedidoDTO.getCodigo() != null) {
            pedido.setCodigo(pedidoDTO.getCodigo());
        } else {
            pedido.setCodigo("TEMP");
        }

        BigDecimal total = BigDecimal.ZERO;
        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedidoDTO dto : pedidoDTO.getItens()) {
            Produto produto = produtoRepository.findById(dto.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(dto.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setPedido(pedido);

            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(dto.getQuantidade()));
            total = total.add(subtotal);

            itens.add(item);
        }

        pedido.setItens(itens);
        pedido.setValorTotal(total);

        Pedido salvo = pedidoRepository.save(pedido);
        return toDTO(salvo);
    }


    private PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setClienteId(pedido.getCliente().getId());
        dto.setCodigo(pedido.getCodigo());
        dto.setData(pedido.getData());
        dto.setValorTotal(pedido.getValorTotal());

        List<ItemPedidoDTO> itensDTO = new ArrayList<>();
        for (ItemPedido item : pedido.getItens()) {
            ItemPedidoDTO itemDTO = new ItemPedidoDTO();
            itemDTO.setProdutoId(item.getProduto().getId());
            itemDTO.setQuantidade(item.getQuantidade());
            itensDTO.add(itemDTO);
        }

        dto.setItens(itensDTO);
        return dto;
    }


    public List<PedidoDTO> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> dtos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            dtos.add(toDTO(pedido));
        }
        return dtos;
    }

    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return toDTO(pedido);
    }
}
