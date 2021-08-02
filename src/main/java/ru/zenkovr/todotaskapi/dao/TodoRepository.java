package ru.zenkovr.todotaskapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zenkovr.todotaskapi.model.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
