package com.sellers.service;

import com.sellers.dto.ProductDto;
import com.sellers.dto.SoldDto;
import com.sellers.dto.UserDto;
import com.sellers.entities.Product;
import com.sellers.entities.Sold;
import com.sellers.producer.ValidEmailProducer;
import com.sellers.repositories.ProductRepository;
import com.sellers.repositories.SoldRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class SoldService {

    @Autowired
    private SoldRepository soldRepository;
    private OauthService oauthService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ValidEmailProducer validEmailProducer;

    public Sold create(String token, SoldDto soldDto){
        try{
            log.info("Begin create new sold {}", token);
            UserDto user = this.oauthService.getUserByToken(token);
            return this.soldRepository.save(Sold.builder()
                            .user(user.getId())
                            .product(soldDto.getProduct())
                            .amount(soldDto.getAmount())
                            .total(soldDto.getTotal())
                            .units(soldDto.getUnits())
                            .paidOut(soldDto.getPaidOut())
                            .status(soldDto.getStatus())
                            .estimates(soldDto.getEstimates())
                            .delivery(soldDto.getDelivery())
                            .start(soldDto.getStart())
                            .dueDate(soldDto.getDueDate())
                            .createdDate(LocalDateTime.now())
                            .lastUpdatedDate(LocalDateTime.now())
                    .build());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new Sold with token: " + token);
        }
    }

    public Sold findById(UUID sold){
        try {
            return this.soldRepository.findById(sold).orElseThrow(
                    () -> new NotFoundException("Not Found sold")
            );
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR not found sold in DB with id: " + sold);
        }
    }

    public Boolean validSoldFromUser(Sold sold, UserDto userDto){
        try {
            return sold.getUser().equals(userDto.getId());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR Product no is of user with id: " + userDto.getId());
        }
    }

    public Sold update(String token, SoldDto soldDto){
        try {
            UserDto user = this.oauthService.getUserByToken(token);
            Sold sold = findById(soldDto.getId());
            this.validSoldFromUser(sold,user);

            sold.setPaidOut(true);
            sold.setLastUpdatedDate(LocalDateTime.now());

            return this.soldRepository.save(sold);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on update in Sold of Id: " + soldDto.getId());
        }
    }

    public Boolean delete(String token, SoldDto soldDto){
        try {
            UserDto user = this.oauthService.getUserByToken(token);
            Sold sold = findById(soldDto.getId());
            this.validSoldFromUser(sold,user);

            sold.setStatus("REMOVIDO");

            this.soldRepository.save(sold);

            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on delete in Sold of Id: " + soldDto.getId());
        }
    }

    public Page<Sold> findAllByUser(String token, Integer page, Integer linesPerPage){
        UserDto user = this.oauthService.getUserByToken(token);
        try {
            Pageable paging = PageRequest.of(page, linesPerPage);
            Page<Sold> solds = this.soldRepository.findAllByUser(user.getId() ,paging);

            return solds;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on find pages of Sold this Token-User: " + user.getId());
        }
    }

}
