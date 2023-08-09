package com.notification.integration;

import com.notification.dto.SoldDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "sold", path = "/payment")
public interface SoldIntegration {

    @PostMapping(value = "/valid")
    void createPayment(@RequestBody SoldDto soldDto);
}
