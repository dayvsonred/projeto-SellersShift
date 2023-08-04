package com.product.dto;

import com.product.entities.Todo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoAllForAdmin {

    private List<Todo> a;
}
