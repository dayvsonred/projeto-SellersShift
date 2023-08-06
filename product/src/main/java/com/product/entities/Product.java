package com.product.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_PRODUCT", schema = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String type;
    private Boolean active;
    private UUID user;
    private String offshoot;
    private Integer views;
    private String latitude;
    private String longitude;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Images> images;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime start;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_updated_date", nullable = false)
    private LocalDateTime lastUpdatedDate;





}
