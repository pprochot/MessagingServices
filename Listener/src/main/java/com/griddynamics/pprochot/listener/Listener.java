package com.griddynamics.pprochot.listener;

import common.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Listener {

    private static final Logger logger = Logger.getLogger("Listener");

    private Long timestamp;

    @RabbitListener(queues = "q.message")
    public void onMessage(Order order) {
        if (timestamp == null)
            timestamp = System.currentTimeMillis();
        logger.info((System.currentTimeMillis() - timestamp) + " : " + order.toString());
    }

}
