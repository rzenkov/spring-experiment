package ru.zenkovr.todotaskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zenkovr.todotaskapi.action.CreateTodoAction;
import ru.zenkovr.todotaskapi.action.GetAllTodosAction;
import ru.zenkovr.todotaskapi.dto.request.CreateTodoRequest;
import ru.zenkovr.todotaskapi.dto.request.UpdateTodoRequest;
import ru.zenkovr.todotaskapi.dto.response.TodoDTO;
import ru.zenkovr.todotaskapi.dto.response.TodoCollection;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final CreateTodoAction createTodoAction;
    private final GetAllTodosAction getAllTodosAction;

    @Autowired
    public TodoController(CreateTodoAction createTodoAction, GetAllTodosAction getAllTodosAction){
        this.createTodoAction=createTodoAction;
        this.getAllTodosAction = getAllTodosAction;
    }

    @GetMapping()
    public ResponseEntity<TodoCollection> showAllTodos(Pageable pageable){
       TodoCollection todos = getAllTodosAction.handle(pageable);

       return ResponseEntity.ok(todos);
    }

    @PostMapping()
    public ResponseEntity<TodoDTO> showTodo(@Valid @RequestBody CreateTodoRequest createTodoRequest)
    {
        TodoDTO todo = createTodoAction.handle(createTodoRequest);

        return new ResponseEntity(todo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public TodoDTO createTodo(@PathVariable("id") Integer todoId){
        return new TodoDTO();
    }

    @PutMapping("/{id}")
    public TodoDTO updateTodo(@RequestBody UpdateTodoRequest todo, Integer todoId){
        return new TodoDTO();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(Integer todoId){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
