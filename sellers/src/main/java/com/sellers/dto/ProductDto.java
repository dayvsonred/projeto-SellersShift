package com.sellers.dto;

import com.sellers.entities.Images;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private UUID id;
    private String name;
    private String type;
    private Boolean active;
    private UUID user;
    private String offshoot;
    private BigDecimal amount;
    private Integer units;
    private String description;
    private Integer views;
    private String latitude;
    private String longitude;
    private List<Images> images;
    private LocalDateTime start;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
