package dev.monczall.itconference.exception;

import dev.monczall.itconference.exception.exceptions.*;
import dev.monczall.itconference.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    final HttpStatus CONFLICT = HttpStatus.CONFLICT;

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAttendeeNotFoundException(AttendeeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND.value(), ex.getMessage());

        return ResponseEntity.status(NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(LectureAtFullCapacityException.class)
    public ResponseEntity<ErrorResponse> handleLectureAtFullCapacityException(LectureAtFullCapacityException ex) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.value(), ex.getMessage());

        return ResponseEntity.status(BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(AttendeeLoginAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> handleAttendeeLoginAlreadyInUseException(AttendeeLoginAlreadyInUseException ex) {
        ErrorResponse errorResponse = new ErrorResponse(CONFLICT.value(), ex.getMessage());

        return ResponseEntity.status(CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(MissingDataException.class)
    public ResponseEntity<ErrorResponse> handleMissingDataException(MissingDataException ex) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.value(), ex.getMessage());

        return ResponseEntity.status(BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(AttendeeIsBusyAtTheTimeException.class)
    public ResponseEntity<ErrorResponse> handleAttendeeIsBusyAtTheTimeException(AttendeeIsBusyAtTheTimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(CONFLICT.value(), ex.getMessage());

        return ResponseEntity.status(CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(LectureNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLectureNotFoundException(LectureNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND.value(), ex.getMessage());

        return ResponseEntity.status(NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(EmailBadlyFormattedException.class)
    public ResponseEntity<ErrorResponse> handleEmailBadlyFormattedException(EmailBadlyFormattedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.value(), ex.getMessage());

        return ResponseEntity.status(BAD_REQUEST)
                .body(errorResponse);
    }
}
