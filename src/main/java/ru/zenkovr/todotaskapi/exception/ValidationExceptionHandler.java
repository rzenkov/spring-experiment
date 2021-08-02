package ru.zenkovr.todotaskapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
@RestController
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), null);
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(StudentNotFoundException.class)
//    public final ResponseEntity<Object> handleUserNotFoundException(StudentNotFoundException ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        ValidationExceptionResponse exceptionResponse = ValidationExceptionResponse.builder()
                .details("Validation failed")
                .timestamp(new Date())
                .message(request.getDescription(false))
                .errors(
                        ex.getFieldErrors().stream().map(
                                fieldError -> {
                                    return new FieldError(
                                            fieldError.getField().toString(),
                                            fieldError.getDefaultMessage()
                                    );
                                }).collect(Collectors.toList())
                ).build();

        return new ResponseEntity(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}