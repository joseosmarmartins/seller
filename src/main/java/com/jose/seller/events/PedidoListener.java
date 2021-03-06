package com.jose.seller.events;

import com.jose.seller.models.Evento;
import com.jose.seller.models.OrdemTransporte;
import com.jose.seller.models.Pedido;
import com.jose.seller.services.EventoService;
import com.jose.seller.services.OrdemTransporteService;
import com.jose.seller.services.PedidoService;
import com.jose.seller.utils.EventoJsonAuxiliar;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
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
    public void pedidoCriado(@Payload String pedidoId, @Header(AmqpHeaders.CORRELATION_ID) String correlationId) {
        try {
            Pedido pedido = pedidoService.getPorId(Long.valueOf(pedidoId));

            criarOrdemTransporte(pedido);
        } catch (Exception e) {
            salvaEventoErro(e.getMessage(), correlationId);
            return;
        }

        salvaEventoSucesso("Ordem de transporte criada com sucesso!", correlationId);
    }

    private void criarOrdemTransporte(Pedido pedido) {
        OrdemTransporte ordemTransporte = new OrdemTransporte();
        ordemTransporte.setPedido(pedido);
        ordemTransporte.setStatus("PENDING");

        ordemTransporteService.salvar(ordemTransporte);
    }

    private void salvaEventoSucesso(String message, String correlationId) {
        Evento evento = new Evento();
        EventoJsonAuxiliar auxiliar = new EventoJsonAuxiliar(correlationId, new Date(), message, true);
        evento.setEventoJsonAuxiliar(auxiliar);

        eventoService.salvar(evento);
    }

    private void salvaEventoErro(String message, String correlationId) {
        Evento evento = new Evento();
        EventoJsonAuxiliar auxiliar = new EventoJsonAuxiliar(correlationId, new Date(), message, false);
        evento.setEventoJsonAuxiliar(auxiliar);

        eventoService.salvar(evento);
    }
}
