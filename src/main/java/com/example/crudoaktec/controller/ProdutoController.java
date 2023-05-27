package com.example.crudoaktec.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudoaktec.entities.Produto;
import com.example.crudoaktec.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable UUID id) {
        Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
        return produto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable UUID id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/busca/{nome}")
    public ResponseEntity<List<Produto>> buscarProdutosPorNome(@PathVariable String nome) {
        List<Produto> produtos = produtoService.buscarProdutosPorNome(nome);
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos);
    }

}