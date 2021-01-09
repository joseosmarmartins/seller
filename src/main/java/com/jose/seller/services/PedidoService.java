package com.jose.seller.services;

import com.jose.seller.models.Pedido;
import com.jose.seller.models.Produto;
import com.jose.seller.repository.IPedidoRepository;
import com.jose.seller.utils.NegocioException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    private final IPedidoRepository iPedidoRepository;
    private final ProdutoService produtoService;

    public PedidoService(RabbitTemplate rabbitTemplate, Queue queue, IPedidoRepository iPedidoRepository, ProdutoService produtoService) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.iPedidoRepository = iPedidoRepository;
        this.produtoService = produtoService;
    }

    public Long salvar(Pedido pedido) throws NegocioException {
        validation(pedido);

        Integer total = 0;
        for (Produto produto : pedido.getProdutos()) {
            produto = produtoService.getPorId(produto.getId());
            total += produto.getValor();
        }
        pedido.setValorTotal(total);

        try {
            pedido = iPedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }

        rabbitTemplate.convertAndSend(this.queue.getName(), pedido.getId() + "");

        return pedido.getId();
    }

    public Pedido getPorId(Long id) {
        return iPedidoRepository.getOne(id);
    }

    public List<Pedido> getPedidosValorTotalMaiorIgualQuinhentos() {
        return iPedidoRepository.findByValorTotalGreaterThanEqual(50000);
    }

    private void validation(Pedido pedido) throws NegocioException {
        if (pedido.getProdutos() == null || pedido.getProdutos().size() < 1)
            throw new NegocioException("Pedido precisa ter no mínimo 1 produto");
        if (pedido.getProdutos().size() > 3) throw new NegocioException("Pedido só pode ter no máximo 3 produtos");
    }
}
