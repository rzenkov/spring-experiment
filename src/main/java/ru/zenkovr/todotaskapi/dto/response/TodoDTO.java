package ru.zenkovr.todotaskapi.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zenkovr.todotaskapi.model.Todo;

import java.util.Date;

@Data
@NoArgsConstructor
public class TodoDTO {
    private Long id;
    private String title;
    private String description;
    private Date createdAt;
    public TodoDTO(Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.createdAt = todo.getCreatedAt();
    }
}
