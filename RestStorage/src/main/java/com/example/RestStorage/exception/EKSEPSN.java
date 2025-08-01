package com.example.RestStorage.exception;

import com.example.RestStorage.model.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EKSEPSN {

    @ExceptionHandler(IllegalArgumentException.class)

    public ResponseEntity<String> handlerEksepsna(IllegalArgumentException e) {


        String message = e.getMessage();

        if (message == null || message.isEmpty()) {
            return new ResponseEntity<>("There was an error" , HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<String> handlerFormata(Exception ex) {
        return new ResponseEntity<>("""
                Invalid format
                
                Name must be String
                
                Category must be String
                
                Count must be a number
                
                Price must be a number
                
                Location must be one of the following:
                  MAGACIN_E
                  MAGACIN_G
                  MAGACIN_H
                  MAGACIN_P
                """, HttpStatus.BAD_REQUEST);
    }

}
