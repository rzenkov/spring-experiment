package ru.zenkovr.todotaskapi.action;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zenkovr.todotaskapi.dao.TodoRepository;
import ru.zenkovr.todotaskapi.dto.response.TodoCollection;
import ru.zenkovr.todotaskapi.model.Todo;

@Service
public class GetAllTodosAction {
    private final TodoRepository repository;

    public GetAllTodosAction(TodoRepository repository) {
        this.repository = repository;
    }

    public TodoCollection handle(Pageable pageable) {
        Page<Todo> todos = repository.findAll(pageable);

        return new TodoCollection(todos);
    }
}
