package com.sellers.producer;

import com.sellers.dto.UserDto;
import com.sellers.entities.Sold;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentProducer {

    @Value("${rabbitmq.payment.exchange}")
    private String exchange;

    @Value("${rabbitmq.payment.routing}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Sold message){
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            log.info("New event send event exec payment id: {}", message.getId());
        }catch ( Exception e){
            log.info("ERROR send event valid email id: {} ", message.getId());
            throw new RuntimeException(e);
        }
    }
}
