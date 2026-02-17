package org.example.webstore.security;

import org.example.webstore.exception.ItemNotFoundException;
import org.example.webstore.security.model.ErrorResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MS_PATH = "webstore-backend";

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

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleItemNotFound(ItemNotFoundException ex) {
        var errorResponse = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),
                "item-not-found", ex.getMessage(), MS_PATH, ex.toString(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidDataAccessResourceUsage(InvalidDataAccessResourceUsageException ex) {
        var errorResponse = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),
                "sql-error", ex.getMessage(), MS_PATH, ex.toString(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleInternalServerError(Exception ex) {
        var errorResponse = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),
                "internal-server-error", ex.getMessage(), MS_PATH, ex.toString(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
