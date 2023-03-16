package com.noxfl.leafrake.amqp;

import com.noxfl.leafrake.LeafRakeConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fernando Nathanael
 *
 */
@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(LeafRakeConfiguration.OUTPUT_QUEUE_NAME, message);
        System.out.println(" [x] Sent message");
    }

}
