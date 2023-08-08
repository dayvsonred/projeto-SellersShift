package com.sellers.resource;

import com.sellers.dto.SoldDto;
import com.sellers.entities.Sold;
import com.sellers.service.SoldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/payment")
public class PaymentResource {

    @Autowired
    SoldService soldService;

    @PostMapping(value = "/valid")
    public ResponseEntity<HttpStatus> create(
            @RequestBody @Valid SoldDto soldDto) {
        this.soldService.createPayment(soldDto);
        return ResponseEntity.ok().build();
    }

}
