package com.jose.seller.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(NegocioException.class)
    private ResponseEntity<ExceptionMessage> handleMessage(NegocioException negocioExpection) {
        return ResponseEntity.status(400).body(ExceptionMessage.builder().message(negocioExpection.getMessage()).build());
    }
}
