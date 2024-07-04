package me.dio.hiokdev.restaurant_orders.customer_api.api.exceptionhandler;

import me.dio.hiokdev.restaurant_orders.customer_api.api.dto.ErrorDetails;
import me.dio.hiokdev.restaurant_orders.customer_api.domain.exception.CustomerException;
import me.dio.hiokdev.restaurant_orders.customer_api.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        var errorDetails = ErrorDetails.builder()
                .status(status.value())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDetails> customerExceptionHandler(CustomerException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorDetails = ErrorDetails.builder()
                .status(status.value())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorDetails = ErrorDetails.builder()
                .status(status.value())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }

}
