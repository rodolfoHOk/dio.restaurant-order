package me.dio.hiokdev.restaurant_orders.order_service.api.exceptionhandler;

import lombok.RequiredArgsConstructor;
import me.dio.hiokdev.restaurant_orders.order_service.api.dto.ErrorDetails;
import me.dio.hiokdev.restaurant_orders.order_service.domain.exception.OrderException;
import me.dio.hiokdev.restaurant_orders.order_service.domain.exception.ResourceNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var messageBuilder = new StringBuilder();
        ex.getFieldErrors().forEach(fieldError -> {
            messageBuilder.append("campo ");
            messageBuilder.append(fieldError instanceof FieldError error ? error.getField() : fieldError.getObjectName());
            messageBuilder.append(": ");
            messageBuilder.append(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
            messageBuilder.append("; ");
        });
        var errorDetails = ErrorDetails.builder()
                .status(status.value())
                .message(messageBuilder.toString())
                .details(request.getDescription(false))
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDetails> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        var errorDetails = ErrorDetails.builder()
                .status(status.value())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorDetails> customerExceptionHandler(OrderException ex, WebRequest request) {
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
                .message(ex.getClass().getSimpleName() + ": " + ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }

}
