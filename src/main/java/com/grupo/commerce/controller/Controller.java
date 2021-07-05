package com.grupo.commerce.controller;

import com.grupo.commerce.controller.forms.PedidoForm;
import com.grupo.commerce.models.Pedido;
import com.grupo.commerce.repository.PedidoRepository;
import com.grupo.commerce.repository.ProdutoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientepedido")
public class Controller {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping()
    public ResponseEntity<Pedido> buyProduct(@RequestBody @Valid PedidoForm pedidoForm, UriComponentsBuilder uriBuilder){
        Pedido pedido = pedidoForm.converter(produtoRepository);
        URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
        pedidoRepository.save(pedido);
        return ResponseEntity.created(uri).body(pedido);
    }

    @GetMapping
    public List<Pedido> getAllPedido(){
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pedido.get());

    }
}
