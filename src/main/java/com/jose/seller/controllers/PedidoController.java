package com.jose.seller.controllers;

import com.jose.seller.models.Pedido;
import com.jose.seller.services.PedidoService;
import com.jose.seller.utils.NegocioException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Long salvar(@RequestBody Pedido pedido) throws NegocioException {
        return pedidoService.salvar(pedido);
    }

    @GetMapping("/valorTotalMaiorIgualQuinhentos")
    public List<Pedido> getPedidosValorTotalMaiorIgualQuinhentos() {
        return pedidoService.getPedidosValorTotalMaiorIgualQuinhentos();
    }
}
