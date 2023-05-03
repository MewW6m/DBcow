package com.dbcow.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class CustomErrorException extends RuntimeException {    
    private int statusCode;
    private String message;
}