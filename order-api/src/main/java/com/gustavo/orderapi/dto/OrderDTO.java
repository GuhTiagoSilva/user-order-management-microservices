package com.gustavo.orderapi.dto;

import com.gustavo.orderapi.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String description;
    private UserDTO user;

    public OrderDTO(Order entity, UserDTO userDTO) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        user = userDTO;
    }
}
