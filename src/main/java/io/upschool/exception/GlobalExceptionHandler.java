package io.upschool.exception;

import io.upschool.dto.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        final var errorMessage = MessageFormat.format(
                "No handler found for {0} {1}", ex.getHttpMethod(), ex.getRequestURL());
        
        log.warn("No handler found - Method: {}, URL: {}", ex.getHttpMethod(), ex.getRequestURL());
        
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .isSuccess(false)
                .error(errorMessage)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Business Logic Exceptions - 400 Bad Request
    @ExceptionHandler({
            AirlineAlreadySavedException.class,
            AirportAlreadySavedException.class,
            RouteAlreadySavedException.class,
            TicketAlreadySavedException.class,
            InvalidCreditCardNumberException.class,
            InvalidRouteException.class
    })
    public ResponseEntity<Object> handleBusinessLogicExceptions(
            RuntimeException ex, HttpServletRequest request) {
        
        log.warn("Business logic exception occurred - {}: {} | Path: {} | Method: {}", 
                ex.getClass().getSimpleName(), ex.getMessage(), 
                request.getRequestURI(), request.getMethod());
        
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccess(false)
                .error(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    // Not Found Exceptions - 404 Not Found
    @ExceptionHandler({
            AirlineNotFoundException.class,
            AirportNotFoundException.class,
            FlightNotFoundException.class,
            RouteNotFoundException.class,
            TicketNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(
            RuntimeException ex, HttpServletRequest request) {
        
        log.warn("Resource not found - {}: {} | Path: {} | Method: {}", 
                ex.getClass().getSimpleName(), ex.getMessage(), 
                request.getRequestURI(), request.getMethod());
        
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .isSuccess(false)
                .error(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Insufficient Resources Exceptions - 409 Conflict
    @ExceptionHandler(InsufficientSeatsException.class)
    public ResponseEntity<Object> handleInsufficientSeatsException(
            InsufficientSeatsException ex, HttpServletRequest request) {
        
        log.warn("Insufficient resources - {}: {} | Path: {} | Method: {}", 
                ex.getClass().getSimpleName(), ex.getMessage(), 
                request.getRequestURI(), request.getMethod());
        
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.CONFLICT.value())
                .isSuccess(false)
                .error(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Generic RuntimeException - 400 Bad Request
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        
        log.error("Runtime exception occurred - {}: {} | Path: {} | Method: {}", 
                ex.getClass().getSimpleName(), ex.getMessage(), 
                request.getRequestURI(), request.getMethod(), ex);
        
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccess(false)
                .error(ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred")
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    // Generic Exception Handler - 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(
            Exception ex, HttpServletRequest request) {
        
        log.error("Unexpected exception occurred - {}: {} | Path: {} | Method: {} | StackTrace: ", 
                ex.getClass().getSimpleName(), ex.getMessage(), 
                request.getRequestURI(), request.getMethod(), ex);
        
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .isSuccess(false)
                .error("An internal server error occurred. Please contact support.")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}