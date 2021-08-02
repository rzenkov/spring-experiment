package ru.zenkovr.todotaskapi.dto.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateTodoRequest {
    @NotBlank
    @Size(min=3,max=50)
    private String title;
    @NotNull
    @Size(max=200)
    private String description;
}
