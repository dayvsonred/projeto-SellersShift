package com.product.producer;

import com.product.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidEmailProducer {

    @Value("${rabbitmq.valid-email.exchange}")
    private String exchange;

    @Value("${rabbitmq.valid-email.routing}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(UserDto message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            log.info("New event send event valid email id: {}", message.getId());
        }catch ( Exception e){
            log.info("ERROR send event valid email id: {} ", message.getId());
            throw new RuntimeException(e);
        }
    }
}
