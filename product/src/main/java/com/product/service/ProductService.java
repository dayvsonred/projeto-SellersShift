package com.product.service;

import com.product.dto.ProductDto;
import com.product.dto.UserDto;
import com.product.entities.Images;
import com.product.entities.Product;
import com.product.producer.ValidEmailProducer;
import com.product.repositories.ProductRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private OauthService oauthService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ValidEmailProducer validEmailProducer;

    public Product create(String token, ProductDto productDto){
        try{
            log.info("Begin create new user {}", token);
            UserDto user = this.oauthService.getUserByToken(token);
            return this.productRepository.save(Product.builder()
                            .name(productDto.getName())
                            .type(productDto.getType())
                            .active(true)
                            .user(user.getId())
                            .offshoot(productDto.getOffshoot())
                            .views(productDto.getViews())
                            .latitude(productDto.getLatitude())
                            .longitude(productDto.getLongitude())
                            .images(productDto.getImages())
                            .start(LocalDateTime.now())
                    .build());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new Product with token: " + token);
        }
    }

    public Product findById(UUID product){
        try {
            return this.productRepository.findById(product).orElseThrow(
                    () -> new NotFoundException("Not Found product")
            );
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR not found product in DB with id: " + product);
        }
    }

    public Boolean validProductFromUser(Product product, UserDto userDto){
        try {
            return product.getUser().equals(userDto.getId());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR Product no is of user with id: " + userDto.getId());
        }
    }

    public Product update(String token, ProductDto productDto){
        try {
            UserDto user = this.oauthService.getUserByToken(token);
            Product product = findById(productDto.getId());
            this.validProductFromUser(product,user);

            product.setActive(productDto.getActive());
            product.setName(productDto.getName());
            product.setStart(productDto.getStart());

            return this.productRepository.save(product);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on update in Product of Id: " + productDto.getId());
        }
    }

    public Boolean delete(String token, ProductDto productDto){
        try {
            UserDto user = this.oauthService.getUserByToken(token);
            Product product = findById(productDto.getId());
            this.validProductFromUser(product,user);

            product.setActive(false);

            this.productRepository.save(product);

            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on delete in Product of Id: " + productDto.getId());
        }
    }

}
