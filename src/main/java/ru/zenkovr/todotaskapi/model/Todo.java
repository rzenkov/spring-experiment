package ru.zenkovr.todotaskapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zenkovr.todotaskapi.dto.request.CreateTodoRequest;
import ru.zenkovr.todotaskapi.dto.request.RegisterUserRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name="todos")
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3,max=50)
    private String title;

    @NotBlank
    @Size (max=200)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Todo(CreateTodoRequest createTodoRequest){
        this.title = createTodoRequest.getTitle();
        this.description = createTodoRequest.getDescription();
        this.createdAt=new Date();
    }

}
