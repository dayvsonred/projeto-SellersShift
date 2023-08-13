package com.notification.consumer;

import com.google.gson.Gson;
import com.notification.dto.UserDetails;
import com.notification.dto.UserDto;
import com.notification.service.ValidEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidEmailConsumer {

    @Autowired
    ValidEmailService validEmailService;

    @RabbitListener(queues = {"${rabbitmq.valid-email.queue}"})
    public void paymentQueue(String message) {
        try {
            log.info("**********************************************************");
            Gson gson = new Gson();
            UserDetails userDto = gson.fromJson(message, UserDetails.class);
            log.info("soldDto : {}", userDto);
            this.validEmailService.sendEmailToValidEmail(userDto);
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception e) {
            log.error("ERROR Critical receive message add Like paymentQueue {}", message);
            log.error(e.getMessage(), e);
        }
    }
}
