package com.jose.seller.repository;

import com.jose.seller.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByValorTotalGreaterThanEqual(Integer value);
}
