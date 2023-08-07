package com.sellers.entities;

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
@Entity
@Table(name = "TB_PAYMENT", schema = "sellers")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Sold sold;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    private Boolean confirmed;
    private UUID confirmed_payment_id;
    private Long attempts;
    private String currency;
    private String amount;
    private String order_id;
    private String status;
    private String received_at;
    private String authorization_code;
    private Boolean delayed;
    private String authorized_at;
    private String reason_message;
    private String acquirer;
    private String brand;
    private String terminal_nsu;
    private String acquirer_transaction_id;
    private String transaction_id;
    private String first_installment_amount;
    private String other_installment_amount;
    private String total_installment_amount;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_updated_date", nullable = false)
    private LocalDateTime lastUpdatedDate;
}
