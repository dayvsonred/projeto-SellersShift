package com.notification.service;

import com.notification.dto.SoldDto;
import com.notification.integration.SoldIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidEmailService {

    @Autowired
    SoldIntegration soldIntegration;

    public void create(SoldDto soldDto){
        log.info("send payment create soldDto");
        this.soldIntegration.createPayment(soldDto);
    }
}
