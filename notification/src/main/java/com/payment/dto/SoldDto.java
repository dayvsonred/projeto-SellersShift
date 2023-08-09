package com.payment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoldDto {

    private UUID id;
    private UUID user;
    private UUID product;
    private BigDecimal amount;
    private BigDecimal total;
    private Integer units;
    private Boolean paidOut;
    private String status;
    private LocalDateTime estimates;
    private LocalDateTime delivery;
    private LocalDateTime start;
    private LocalDateTime dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
