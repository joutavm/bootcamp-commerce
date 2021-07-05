package com.grupo.commerce.controller.forms;

import com.grupo.commerce.models.Categoria;
import com.grupo.commerce.models.Pedido;
import com.grupo.commerce.models.Produto;
import com.grupo.commerce.repository.CategoriaRepository;
import com.grupo.commerce.repository.ProdutoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoForm {

    @NotNull @NotEmpty
    private String nomeCliente;

    @NotNull @NotEmpty
    private List<String> nomeProdutos;

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setNomeProdutos(List<String> nomeProdutos) {
        this.nomeProdutos = nomeProdutos;
    }

    private List<Produto> converteNomesProdutos(ProdutoRepository produtoRepository){
        return nomeProdutos.stream().map(produtoRepository::findByNome).collect(Collectors.toList());
    }

    public Pedido converter(ProdutoRepository produtoRepository){
        return new Pedido(nomeCliente, converteNomesProdutos(produtoRepository));
    }


}
