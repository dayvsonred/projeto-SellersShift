package com.core.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDto {

    private UUID id;
    private Boolean emailValid;
    private UUID emailValidCode;
    private LocalDateTime dueEmailValid;
    private Boolean cpfValid;
    private UUID cpfValidCode;
    private LocalDateTime dueCpfValid;
}
