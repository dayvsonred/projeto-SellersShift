package com.notification.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String latitude;
    private String longitude;
    private String offshoot;
    private String cpf;
    private Set<String> roles = new HashSet<>();
    private Boolean active;
    private UserDetailsDto userDetails;

}
