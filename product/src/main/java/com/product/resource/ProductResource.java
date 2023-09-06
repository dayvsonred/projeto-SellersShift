package com.product.resource;

import com.product.dto.ProductDto;
import com.product.dto.UserDto;
import com.product.entities.Product;
import com.product.entities.ProductViews;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(value = "/test")
    public ResponseEntity<Product> test(
            @RequestHeader(value = "Authorization") String token) {
            log.info("Product test ok");
        return ResponseEntity.ok(Product.builder().type("ok").name("produt ok").build());
    }

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

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Product>> pageFindAllProduct(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage
    ) {
        return ResponseEntity.ok(this.productService.findProductAllByUserTodo(token, page, linesPerPage));
    }

    @GetMapping(value = "add/views/{product}")
    public ResponseEntity<ProductViews> addViews(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable UUID product) {
        log.info("Add views to product {}", product);
        return ResponseEntity.ok(this.productService.addViewFromProduct(token, product));
    }
    //get by coordenades proximas
}
