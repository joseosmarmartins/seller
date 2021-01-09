package com.jose.seller.services;

import com.jose.seller.models.OrdemTransporte;
import com.jose.seller.repository.IOrdemTransporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemTransporteService {

    private final IOrdemTransporteRepository iOrdemTransporteRepository;
    private final PedidoService pedidoService;

    public OrdemTransporteService(IOrdemTransporteRepository iOrdemTransporteRepository, PedidoService pedidoService) {
        this.iOrdemTransporteRepository = iOrdemTransporteRepository;
        this.pedidoService = pedidoService;
    }

    public Long salvar(OrdemTransporte ordemTransporte) {
        ordemTransporte.setPedido(pedidoService.getPorId(ordemTransporte.getPedido().getId()));
        return iOrdemTransporteRepository.save(ordemTransporte).getId();
    }

    public List<OrdemTransporte> get() {
        return iOrdemTransporteRepository.findAll();
    }
}
