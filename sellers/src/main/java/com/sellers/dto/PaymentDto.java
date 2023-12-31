package com.sellers.dto;

import com.sellers.entities.Sold;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    private UUID id;
    private Sold sold;
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
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
