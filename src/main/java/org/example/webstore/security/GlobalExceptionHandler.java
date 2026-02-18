package org.example.webstore.security;

import jakarta.servlet.http.HttpServletRequest;
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

    private final ErrorResponseHandler errorResponseHandler;

    public GlobalExceptionHandler(ErrorResponseHandler errorResponseHandler) {
        this.errorResponseHandler = errorResponseHandler;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleInternalServerError(Exception ex, HttpServletRequest request) {
        var errorResponse = errorResponseHandler.toErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "internal-server-error", ex, request);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidDataAccessResourceUsage(InvalidDataAccessResourceUsageException ex,
                                                                                 HttpServletRequest request) {
        var errorResponse = errorResponseHandler.toErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "sql-error", ex, request);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicate(DataIntegrityViolationException ex,
                                                            HttpServletRequest request) {
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
//
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body("Unexpected server error: " + ex.toString());

        var errorResponse = errorResponseHandler.toErrorResponse(HttpStatus.CONFLICT,
                "sql-constraint-error", ex, request);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalState(IllegalStateException ex, HttpServletRequest request) {
        var errorResponse = errorResponseHandler.toErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "invalid-state", ex, request);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        var errorResponse = errorResponseHandler.toErrorResponse(HttpStatus.BAD_REQUEST,
                "invalid-argument-in-request", ex, request);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleItemNotFound(ItemNotFoundException ex, HttpServletRequest request) {
        var errorResponse = errorResponseHandler.toErrorResponse(HttpStatus.NOT_FOUND,
                "item-not-found", ex, request);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }


}
