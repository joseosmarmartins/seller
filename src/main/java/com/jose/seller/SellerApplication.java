package com.jose.seller;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SellerApplication {

    @Value("${fila.pedido.nome}")
    private String filaPedido;

    public static void main(String[] args) {
        SpringApplication.run(SellerApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new Queue(filaPedido, true);
    }
}
