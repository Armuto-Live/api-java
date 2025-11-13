package com.lta.apis.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String errorDetails;

    public ErrorResponse(String errorDetails, int statusCode, String message) {
        this.errorDetails = errorDetails;
        this.statusCode = statusCode;
        this.message = message;
    }




}
