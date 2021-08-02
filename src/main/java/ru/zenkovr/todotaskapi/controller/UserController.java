package ru.zenkovr.todotaskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zenkovr.todotaskapi.action.GetAllUsersAction;
import ru.zenkovr.todotaskapi.dto.response.UserCollection;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final GetAllUsersAction getAllUsersAction;
    @Autowired
    public UserController(GetAllUsersAction getAllUsersAction){
        this.getAllUsersAction = getAllUsersAction;
    }
    @GetMapping()
    public ResponseEntity<UserCollection> showAllUsers(Pageable pageable){
        UserCollection userCollection = getAllUsersAction.handle(pageable);
        return ResponseEntity.ok(userCollection);
    }
}
