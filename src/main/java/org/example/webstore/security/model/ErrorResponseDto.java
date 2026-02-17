package org.example.webstore.security.model;

public record ErrorResponseDto (Integer status, String errorCode, String errorMessage, String path,
                                String exceptionString, Exception ex){
}
