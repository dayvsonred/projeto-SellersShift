package com.payment.dto;

import com.payment.entities.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private UUID id;
    private String comment;
    private UUID user;
    private Boolean active;
    private Product product;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
