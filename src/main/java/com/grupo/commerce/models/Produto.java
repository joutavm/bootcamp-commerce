package com.grupo.commerce.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @ManyToMany
    private List<Categoria> categorias;

    public Produto(){}

    public Produto(String nome, List<Categoria> categorias){
        this.nome=nome;
        this.categorias=categorias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        if (id != null ? !id.equals(produto.id) : produto.id != null) return false;
        if (nome != null ? !nome.equals(produto.nome) : produto.nome != null) return false;
        return categorias != null ? categorias.equals(produto.categorias) : produto.categorias == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (categorias != null ? categorias.hashCode() : 0);
        return result;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }


    public List<Categoria> getCategorias(){
        return this.categorias;
    }

    public void setCategorias(List<Categoria> categorias){
        this.categorias=categorias;
    }

    public void addCategoria(Categoria categoria){
        categorias.add(categoria);
    }

}
