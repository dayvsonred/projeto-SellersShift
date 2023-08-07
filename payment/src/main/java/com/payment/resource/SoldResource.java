package com.payment.resource;

import com.payment.dto.SoldDto;
import com.payment.entities.Sold;
import com.payment.service.SoldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/Sold")
public class SoldResource {

    @Autowired
    SoldService soldService;

    @PostMapping
    public ResponseEntity<Sold> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @Valid SoldDto soldDto) {
        return ResponseEntity.ok(this.soldService.create(token, soldDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sold> findById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable UUID id) {
        log.info("Product findById {}", id);
        return ResponseEntity.ok(this.soldService.findById(id));
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Sold>> pageFindAllSold(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage
    ) {
        return ResponseEntity.ok(this.soldService.findAllByUser(token, page, linesPerPage));
    }
}
