package com.product.resource;

import com.product.dto.UserDto;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/Product")
public class ProductResource {

    @Autowired
    ProductService loginService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(this.loginService.createNewUser(userDto));
    }
}
