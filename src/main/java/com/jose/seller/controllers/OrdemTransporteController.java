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
    public List<OrdemTransporte> get() {
        return ordemTransporteService.get();
    }

    // Passando só o ID mesmo a chamada sendo Patch, pois o status está sendo definido diretamente no método do Service
    @PatchMapping("{id}")
    public void iniciarTransporte(@PathVariable("id") Long id) {
        ordemTransporteService.iniciarTransporte(id);
    }
}
