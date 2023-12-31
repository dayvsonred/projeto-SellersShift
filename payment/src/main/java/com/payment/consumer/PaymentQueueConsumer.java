package com.payment.consumer;

import com.payment.dto.SoldDto;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentQueueConsumer {

    @Autowired
    PaymentService paymentService;

    @RabbitListener(queues = {"${rabbitmq.payment.queue}"})
    public void paymentQueue(SoldDto soldDto) {
        try {
            log.info("**********************************************************");
            log.info("soldDto : {}", soldDto);
            this.paymentService.create(soldDto);
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception e) {
            log.error("ERROR Critical receive message add Like paymentQueue {}", soldDto);
            log.error(e.getMessage(), e);
        }
    }
}
