package com.grupo.commerce.controller;

import com.grupo.commerce.controller.forms.ProdutoForm;
import com.grupo.commerce.models.Categoria;
import com.grupo.commerce.models.Produto;
import com.grupo.commerce.repository.CategoriaRepository;
import com.grupo.commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adminproduto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<Produto> addProduto(@RequestBody @Valid ProdutoForm produtoForm, UriComponentsBuilder builder) {
        Produto produto = produtoForm.converter(categoriaRepository);

        URI uri = builder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

        produtoRepository.save(produto);

        return ResponseEntity.created(uri).body(produto);
    }


    @GetMapping
    public List<Produto> getProdutos(){
        return produtoRepository.findAll();
    }


    @PutMapping
    @Transactional
    @RequestMapping("/{id}")
    public ResponseEntity<Produto> alteraProduto(@PathVariable Long id, @RequestBody @Valid ProdutoForm produtoForm) {

         Optional<Produto> optionalProduto = produtoRepository.findById(id);
         if (optionalProduto.isPresent()){

             Produto produto = produtoForm.atualizar(id, produtoRepository, categoriaRepository);
             return ResponseEntity.ok(produto);
         }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProduto(@PathVariable Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id){
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if(optionalProduto.isPresent()){
            return ResponseEntity.ok((optionalProduto.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/busca")
    public List<Produto> getProdutosByCategoria(@RequestParam String categoria){
        return produtoRepository.findAllByCategorias_Nome(categoria);
    }

}
