package com.jose.seller.repository;

import com.jose.seller.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByIdIn(List<Long> ids);
}
