package com.product.dto;

import com.product.entities.Product;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagesDto {

    private UUID id;
    private byte[] image;
    private String name;
    private String type;
    private Product product;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
