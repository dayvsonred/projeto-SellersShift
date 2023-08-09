package com.notification.consumer;

import com.notification.dto.SoldDto;
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

    @RabbitListener(queues = {"${rabbitmq.payment.queue}"})
    public void paymentQueue(UserDto userDto) {
        try {
            log.info("**********************************************************");
            log.info("soldDto : {}", userDto);
            this.validEmailService.sendEmailToValidEmail(userDto);
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception e) {
            log.error("ERROR Critical receive message add Like paymentQueue {}", userDto);
            log.error(e.getMessage(), e);
        }
    }
}
