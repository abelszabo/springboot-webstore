package org.example.webstore.security;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.ExceptionUtils;
import org.example.webstore.security.model.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class ErrorResponseHandler {
    private static final String MS_NAME = "webstore-backend";

//    boolean isDev = true; // FIXME via param or @Profile("dev")

    public ErrorResponseDto toErrorResponse(HttpStatus status, String errorCode, Exception ex) {
        return toErrorResponse(status, errorCode, ex, (String) null);
    }

    public ErrorResponseDto toErrorResponse(HttpStatus status, String errorCode, Exception ex, HttpServletRequest request) {
        return toErrorResponse(status, errorCode, ex, null != request ? request.getRequestURI() : null);
    }

    public ErrorResponseDto toErrorResponse(HttpStatus status, String errorCode, Exception ex, String path) {
        return toErrorResponse(status, errorCode, ex.getMessage(), ex, path);
    }

    public ErrorResponseDto toErrorResponse(HttpStatus status, String errorCode, String errorMessage, Exception ex, String path) {
        String errorId = UUID.randomUUID().toString();

//        String stackTrace = isDev
//                ? ExceptionUtils.getStackTrace(ex)
//                : null;

        List<String> stackTrace = Arrays.stream(ex.getStackTrace()).map(Object::toString).toList();

        return new ErrorResponseDto(Instant.now(), status.value(), errorCode, errorMessage,
                MS_NAME, path, errorId, ex.toString(), stackTrace);
    }
}
