package com.payment.service;

import com.payment.dto.SoldDto;
import com.payment.integration.SoldIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    SoldIntegration soldIntegration;

    public void create(SoldDto soldDto){
        log.info("send payment create soldDto");
        this.soldIntegration.createPayment(soldDto);
    }
}
