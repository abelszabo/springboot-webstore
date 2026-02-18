package org.example.webstore.security.model;

import java.time.Instant;
import java.util.List;

public record ErrorResponseDto (Instant timestamp,
                                Integer status,
                                String errorCode,
                                String errorMessage,
                                String ms,
                                String path,
                                String errorId,
                                String exceptionString,
                                List<String> stackTrace){
}
