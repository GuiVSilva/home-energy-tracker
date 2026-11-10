package br.com.homeenergy.deviceservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<ApiError> handleDeviceNotFound(DeviceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), Map.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage(),
                        (firstMessage, ignored) -> firstMessage
                ));

        return ResponseEntity.badRequest()
                .body(new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpectedException(Exception exception) {
        log.error("Unexpected error while processing request", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "An unexpected error occurred", Map.of()));
    }
}
