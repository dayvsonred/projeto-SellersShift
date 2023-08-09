package com.notification.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_EMAIL", schema = "notification")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String from;
    private String subject;
    private String text;
    private String to;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

}
