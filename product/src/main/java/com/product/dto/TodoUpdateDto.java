package com.product.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoUpdateDto {

    @NotBlank
    private String id;
    @NotBlank
    private String title;
}
