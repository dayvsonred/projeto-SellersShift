package com.payment.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_SOLD", schema = "sellers")
public class Sold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID user;
    private UUID product;
    private BigDecimal amount;
    private BigDecimal total;
    private Integer units;
    private Boolean paidOut;

    private String status;

    @Column(name = "estimates_date", nullable = false)
    private LocalDateTime estimates;

    @Column(name = "delivery_date", nullable = false)
    private LocalDateTime delivery;

    @Column(name = "arrival_date", nullable = false)
    private LocalDateTime start;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_updated_date", nullable = false)
    private LocalDateTime lastUpdatedDate;
}
