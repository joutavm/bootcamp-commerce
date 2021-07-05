package com.grupo.commerce.models;

import javax.persistence.*;


import java.util.List;

@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cliente;
    @ManyToMany()
    private List<Produto> produtos;

    public Pedido(){
    }

    public Pedido(String nomeCliente, List<Produto> produtos){
        this.produtos = produtos;
        this.cliente = nomeCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }


}
