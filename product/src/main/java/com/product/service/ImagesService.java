package com.product.service;

import com.product.dto.ImagesDto;
import com.product.dto.ProductDto;
import com.product.dto.UserDto;
import com.product.entities.Images;
import com.product.entities.Product;
import com.product.repositories.ImagesRepository;
import com.product.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ImagesService {

    @Autowired
    private ImagesRepository imagesRepository;
    private OauthService oauthService;

    public Images create(MultipartFile file, UUID product, String token) throws IOException {
        try {
            UserDto user = this.oauthService.getUserByToken(token);

            return this.imagesRepository.save(Images.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .user(user.getId())
                            .product(Product.builder().id(product).build())
                    .image(file.getBytes()).build());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR create image File from product: "+ product);
        }
    }

    public List<Images> findImagesByProduct(UUID product, String token) throws IOException {
        try {
            return this.imagesRepository.findAllByProduct(Product.builder().id(product).build());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR Find image from product: "+ product);
        }
    }
}
