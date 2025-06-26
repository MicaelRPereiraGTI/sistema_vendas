package com.example.sistema_vendas.service;

import com.example.sistema_vendas.domain.Produto;
import com.example.sistema_vendas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public boolean deletar(Long id) {
        return produtoRepository.findById(id).map(produto -> {
            produtoRepository.delete(produto);
            return true;
        }).orElse(false);
    }

    public Optional<Produto> atualizar(Long id, Produto novoProduto) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setCodigo(novoProduto.getCodigo());
            produto.setNome(novoProduto.getNome());
            produto.setPreco(novoProduto.getPreco());
            produto.setQuantidadeEstoque(novoProduto.getQuantidadeEstoque());
            return produtoRepository.save(produto);
        });
    }
}
