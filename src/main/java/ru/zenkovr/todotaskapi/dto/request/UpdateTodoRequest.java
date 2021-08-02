package ru.zenkovr.todotaskapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateTodoRequest {
    @NotNull
    @Min(3)
    @Max(50)
    private String title;
    @NotNull
    @Min(3)
    @Max(50)
    private String description;
    @NotNull
    private Boolean completed;
}
