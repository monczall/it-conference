package dev.monczall.itconference.exception;

import dev.monczall.itconference.exception.exceptions.AttendeeNotFoundException;
import dev.monczall.itconference.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAttendeeNotFoundException(AttendeeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}
