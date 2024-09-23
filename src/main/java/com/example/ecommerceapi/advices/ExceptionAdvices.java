package com.example.ecommerceapi.advices;

import com.example.ecommerceapi.dtos.ErrorResponseDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionAdvices {
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handleException(Exception e) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setStatus(500);
        errorResponseDTO.setMessage(e.getMessage());
        return errorResponseDTO;
    }
}