package ru.zenkovr.todotaskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zenkovr.todotaskapi.action.ConfirmEmailAction;
import ru.zenkovr.todotaskapi.action.RegisterUserAction;
import ru.zenkovr.todotaskapi.dto.request.RegisterUserRequest;
import ru.zenkovr.todotaskapi.dto.response.Registration;
import ru.zenkovr.todotaskapi.dto.response.UserDTO;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterUserAction registerUserAction;
    private final ConfirmEmailAction confirmEmailAction;

    @Autowired
    public AuthController(RegisterUserAction registerUserAction, ConfirmEmailAction confirmEmailAction){
        this.registerUserAction = registerUserAction;
        this.confirmEmailAction = confirmEmailAction;
    }
    @PostMapping("/register")
    public ResponseEntity<Registration> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) throws MessagingException {
        registerUserAction.handle(registerUserRequest);

        Registration registration = new Registration("Registration done, please confirm email");
        return ResponseEntity.ok(registration);
    }
    @GetMapping("/confirm-email")
    public ResponseEntity<UserDTO> confirmEmail(@RequestParam(name="token") String token) throws MessagingException {
        UserDTO user = confirmEmailAction.handle(token);

        return ResponseEntity.ok(user);
    }
}
