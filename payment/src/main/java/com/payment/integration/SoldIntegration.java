package com.payment.integration;

import com.payment.dto.SoldDto;
import com.payment.dto.oauth.UserTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

@Component
@FeignClient(name = "sold", path = "/payment")
public interface SoldIntegration {

    @PostMapping(value = "/valid")
    void createPayment(@RequestBody SoldDto soldDto);
}
