package com.example.crudoaktec.services;

import com.example.crudoaktec.entities.Produto;
import com.example.crudoaktec.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        Sort sort = Sort.by(Sort.Direction.ASC, "valor");
        return produtoRepository.findAll(sort);
    }

    public Optional<Produto> buscarProdutoPorId(UUID id) {
        return produtoRepository.findById(id);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void excluirProduto(UUID id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> buscarProdutosPorNome(String nome) {
        // Converter o nome para letras minúsculas
        String nomeLowerCase = nome.toLowerCase(Locale.ROOT);
        return produtoRepository.findByNomeIgnoreCaseContaining(nomeLowerCase);
    }

}
