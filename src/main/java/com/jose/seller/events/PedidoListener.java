package com.jose.seller.events;

import com.jose.seller.models.Evento;
import com.jose.seller.models.OrdemTransporte;
import com.jose.seller.models.Pedido;
import com.jose.seller.services.EventoService;
import com.jose.seller.services.OrdemTransporteService;
import com.jose.seller.services.PedidoService;
import com.jose.seller.utils.EventoJsonAuxiliar;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PedidoListener {

    private final OrdemTransporteService ordemTransporteService;
    private final PedidoService pedidoService;
    private final EventoService eventoService;

    public PedidoListener(OrdemTransporteService ordemTransporteService, PedidoService pedidoService, EventoService eventoService) {
        this.ordemTransporteService = ordemTransporteService;
        this.pedidoService = pedidoService;
        this.eventoService = eventoService;
    }

    @RabbitListener(queues = {"${fila.pedido.nome}"})
    public void pedidoCriado(@Payload String pedidoId) {
        try {
            Pedido pedido = pedidoService.getPorId(Long.valueOf(pedidoId));

            criarOrdemTransporte(pedido);
        } catch (Exception e) {
            salvaEventoErro(e.getMessage());
            return;
        }

        salvaEventoSucesso("Ordem de transporte criada com sucesso!");
    }

    private void criarOrdemTransporte(Pedido pedido) {
        OrdemTransporte ordemTransporte = new OrdemTransporte();
        ordemTransporte.setPedido(pedido);
        ordemTransporte.setStatus("DONE");

        ordemTransporteService.salvar(ordemTransporte);
    }

    private void salvaEventoSucesso(String message) {
        Evento evento = new Evento();
        EventoJsonAuxiliar auxiliar = new EventoJsonAuxiliar(new Date(), message, true);
        evento.setEventoJsonAuxiliar(auxiliar);

        eventoService.salvar(evento);
    }

    private void salvaEventoErro(String message) {
        Evento evento = new Evento();
        EventoJsonAuxiliar auxiliar = new EventoJsonAuxiliar(new Date(), message, false);
        evento.setEventoJsonAuxiliar(auxiliar);

        eventoService.salvar(evento);
    }
}
