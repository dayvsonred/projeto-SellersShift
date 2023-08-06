package com.product.service;

import com.product.dto.ProductDto;
import com.product.dto.UserDto;
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

import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ValidEmailProducer validEmailProducer;

    public Product create(ProductDto productDto){
        try{
            log.info("Begin create new user {}", productDto.getName());
            Product product = new Product(
                    null,
                    productDto.getUser()
            );
            return  userRepository.save(product);
        }catch (ResponseStatusException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "ERROR User email already exists in DB  with email: " + userDto.getEmail());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new User with email: " + userDto.getEmail());
        }
    }

    private Boolean isValidateNewUser(UserDto userDto){
        try {
            if(!isNull(existUserDtoByEmail(userDto))){
                throw new RuntimeException("User email already exists in DB !!!");
            }

            return true;
        }catch (RuntimeException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "ERROR User email already exists in DB  with email: " + userDto.getEmail());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new User with email: " + userDto.getEmail());
        }
    }

    private User existUserDtoByEmail(UserDto userDto){
        try {
            return userRepository.findByEmail(userDto.getEmail()).orElse(null);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public User findUserByEmail(String email){
        try {
            return userRepository.findByEmail(email).orElseThrow(
                    () -> new NotFoundException("Not Found User email")
            );
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR User email not found in DB with email: " + email);
        }
    }

    public User findUserById(UUID id){
        try {
            return userRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Not Found User id")
            );
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR User email not found in DB with id: " + id);
        }
    }
}
