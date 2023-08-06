package com.product.resource;

import com.product.dto.ImagesDto;
import com.product.entities.Images;
import com.product.entities.Product;
import com.product.service.ImagesService;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/images")
public class ImagesResource {

    @Autowired
    ImagesService imagesService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Images> findById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable UUID id) {
        log.info("Images findById {}", id);
        return ResponseEntity.ok(Images.builder().build());
    }

    @PostMapping(value = "/product/{product}")
    public ResponseEntity<Images> create(
            @RequestParam("file") MultipartFile file,
            @PathVariable UUID product,
            @RequestHeader(value = "Authorization") String token) throws IOException {
        return ResponseEntity.ok(this.imagesService.create(file, product, token));
    }

    @GetMapping(value = "/product/{product}")
    public ResponseEntity<List<Images>> getImages(
            @PathVariable UUID product,
            @RequestHeader(value = "Authorization") String token) throws IOException {
        return ResponseEntity.ok(this.imagesService.findImagesByProduct(product, token));
    }

}
