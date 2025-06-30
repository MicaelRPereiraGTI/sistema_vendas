package com.example.sistema_vendas.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private Long clienteId;
    private String codigo;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public PedidoDTO(Long id, Long clienteId, String codigo, LocalDateTime data, BigDecimal valorTotal, List<ItemPedidoDTO> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.codigo = codigo;
        this.data = data;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public PedidoDTO(){}
}
