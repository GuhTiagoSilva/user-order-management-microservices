package com.gustavo.userapi.dto;

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
}
