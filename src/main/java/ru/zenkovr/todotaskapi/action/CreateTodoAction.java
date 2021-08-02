package ru.zenkovr.todotaskapi.action;

import org.springframework.stereotype.Service;
import ru.zenkovr.todotaskapi.dao.TodoRepository;
import ru.zenkovr.todotaskapi.dto.request.CreateTodoRequest;
import ru.zenkovr.todotaskapi.dto.response.TodoDTO;
import ru.zenkovr.todotaskapi.model.Todo;

@Service
public class CreateTodoAction {
    private final TodoRepository repository;
    public CreateTodoAction(TodoRepository repository){
        this.repository=repository;
    }
    public TodoDTO handle(CreateTodoRequest createTodoRequest){
        TodoDTO todo = new TodoDTO(createTodo(createTodoRequest));

        return todo;
    }

    public Todo createTodo(CreateTodoRequest createTodoRequest){
        Todo todo = new Todo(createTodoRequest);

        return repository.save(todo);
    }
}
