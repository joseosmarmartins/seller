package com.jose.seller.repository;

import com.jose.seller.models.OrdemTransporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdemTransporteRepository extends JpaRepository<OrdemTransporte, Long> {
}
