package com.grupo.commerce.controller.forms;


import com.grupo.commerce.models.Categoria;
import com.grupo.commerce.models.Produto;
import com.grupo.commerce.repository.CategoriaRepository;
import com.grupo.commerce.repository.ProdutoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoForm {

    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private List<String> nomesCategorias;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomesCategorias(List<String> nomesCategorias) {
        this.nomesCategorias = nomesCategorias;
    }

    private List<Categoria> converteCategorias(CategoriaRepository categoriaRepository){
        return nomesCategorias.stream().map(categoriaRepository::findByNome)
                .collect(Collectors.toList());
    }

    public Produto converter(CategoriaRepository categoriaRepository){
        List<Categoria> categoria = converteCategorias(categoriaRepository);
        return new Produto(nome, categoria);
    }


    public Produto atualizar(Long id, ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        Produto produto = produtoRepository.getById(id);
        produto.setNome(nome);
        produto.setCategorias(converteCategorias(categoriaRepository));
        return produto;
    }
}
