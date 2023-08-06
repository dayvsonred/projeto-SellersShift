package com.product.resource;

import com.product.dto.ProductDto;
import com.product.dto.UserDto;
import com.product.entities.Product;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/product")
public class ProductResource {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(this.productService.create(token, productDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable UUID id) {
        log.info("Product findById {}", id);
        return ResponseEntity.ok(this.productService.findById(id));
    }
}
