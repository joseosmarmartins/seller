package com.jose.seller.controllers;

import com.jose.seller.models.OrdemTransporte;
import com.jose.seller.services.OrdemTransporteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens")
public class OrdemTransporteController {

    private final OrdemTransporteService ordemTransporteService;

    public OrdemTransporteController(OrdemTransporteService ordemTransporteService) {
        this.ordemTransporteService = ordemTransporteService;
    }

    @GetMapping
    public List<OrdemTransporte> getPedidosValorTotalMaiorIgualQuinhentos() {
        return ordemTransporteService.get();
    }
}
