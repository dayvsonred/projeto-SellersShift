package com.notification.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {

    private UUID id;
    private Boolean emailValid;
    private UUID emailValidCode;
    private LocalDateTime dueEmailValid;
    private Boolean cpfValid;
    private UUID cpfValidCode;
    private LocalDateTime dueCpfValid;
    private UserDto user;

}
