package com.example.ecommerceapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private int status;
    private String message;
}
