package com.griddynamics.pprochot.publisher;

import common.Order;
import common.OrderType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.logging.Logger;

@Component
public class Publisher {

    private static final Logger log = Logger.getLogger("Publisher");
    private static final long PUBLISH_RATE = 10_000L;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = PUBLISH_RATE)
    public void send() {
        int id = new Random().nextInt(100000);
        rabbitTemplate.convertAndSend("k.message", new Order(id, "TEST" + id, OrderType.values()[(id % 2)]));
        log.info("Sending completed.");
    }

}
