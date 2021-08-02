package ru.zenkovr.todotaskapi.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import ru.zenkovr.todotaskapi.model.Todo;
import ru.zenkovr.todotaskapi.model.User;

@Data
@NoArgsConstructor
public class TodoCollection {
    Page<TodoDTO> data;

    public TodoCollection(Page<Todo> todos){
        this.data = todos.map(todo -> {
            return new TodoDTO(todo);
        });
    }
}
