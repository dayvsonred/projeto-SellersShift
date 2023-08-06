package com.product.dto;

import com.product.entities.Images;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    private Integer views;
    private String latitude;
    private String longitude;
    private List<Images> images;
    private LocalDateTime start;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
