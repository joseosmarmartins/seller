package com.jose.seller.services;

import com.jose.seller.models.Produto;
import com.jose.seller.repository.IProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final IProdutoRepository IProdutoRepository;

    public ProdutoService(IProdutoRepository IProdutoRepository) {
        this.IProdutoRepository = IProdutoRepository;
    }

    public Produto getPorId(Long id) {
        return IProdutoRepository.getOne(id);
    }

    public List<Produto> get() {
        return IProdutoRepository.findAll();
    }
}
