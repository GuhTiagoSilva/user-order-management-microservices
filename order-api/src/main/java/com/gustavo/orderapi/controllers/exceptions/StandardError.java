package com.gustavo.orderapi.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardError {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
