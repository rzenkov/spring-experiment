package ru.zenkovr.todotaskapi.exception;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ValidationExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    private List<FieldError> errors = null;
}
