package org.example.webstore.security;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicate(DataIntegrityViolationException ex) {
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                //.body("Duplicate order number");
//                .body("Unexpected server error");


//        ErrorResponse error = new ErrorResponse(
//                "USER_NOT_FOUND",
//                ex.getMessage()
//        );

//        ErrorResponse error = new ServerErrorException(
//                "Unexpected server error: " + ex.getMessage(), ex
//        );

//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(error.toString());


        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Unexpected server error: " + ex.toString());
    }
}
