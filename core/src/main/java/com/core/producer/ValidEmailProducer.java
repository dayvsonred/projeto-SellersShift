package com.core.producer;

import com.core.dto.UserDto;
import com.core.entities.User;
import com.google.gson.Gson;
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

    public void sendMessage(User user){
        try {
            Gson gson = new Gson();
            String message = gson.toJson(user, User.class);
            rabbitTemplate. convertAndSend(exchange, routingKey, message);
            log.info("New event send event valid email id: {}", user.getId());
        }catch ( Exception e){
            log.info("ERROR send event valid email id: {} ", user.getId());
            throw new RuntimeException(e);
        }
    }
}
