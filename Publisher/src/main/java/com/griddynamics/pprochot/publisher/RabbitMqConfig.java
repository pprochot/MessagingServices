package com.griddynamics.pprochot.publisher;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class RabbitMqConfig {

    private final AmqpAdmin amqpAdmin;

    public RabbitMqConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @PostConstruct
    public void setUp() {
        var queue = new Queue("q.message", true);
        amqpAdmin.declareQueue(queue);
        var binding = new Binding("q.message", Binding.DestinationType.QUEUE, "ex.message",
                "k.message", new HashMap<>());
        var exchange = new DirectExchange("ex.message");
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareBinding(binding);
    }

}
