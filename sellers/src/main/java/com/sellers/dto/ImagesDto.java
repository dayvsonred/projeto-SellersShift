package com.sellers.dto;

import com.sellers.entities.Product;
import lombok.*;

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
