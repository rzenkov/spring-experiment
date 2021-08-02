package ru.zenkovr.todotaskapi.action;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.zenkovr.todotaskapi.dao.UserRepository;
import ru.zenkovr.todotaskapi.dto.request.RegisterUserRequest;
import ru.zenkovr.todotaskapi.exception.UserAlreadyRegisteredException;
import ru.zenkovr.todotaskapi.model.User;
import ru.zenkovr.todotaskapi.service.EmailSender;

import javax.mail.MessagingException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserActionDTOTest {


    @Test
    void it_throws_exception_when_user_exists() throws MessagingException {
        RegisterUserRequest request = new RegisterUserRequest(
                "username", "password","email@email.ru"
        );

        UserRepository repository = mock(UserRepository.class);
        BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);
        EmailSender sender = mock(EmailSender.class);
        when(repository.findOneByEmail(request.getEmail())).thenReturn(Optional.of(new User()));


        RegisterUserAction action = new RegisterUserAction(repository,encoder, sender);
        assertThrows(UserAlreadyRegisteredException.class, ()->action.handle(request));

    }
    @Test
    void it_creates_user_when_not_exists_and_send_email() throws MessagingException {
        RegisterUserRequest request = new RegisterUserRequest(
                "username", "password","email@email.ru"
        );

        UserRepository repository = mock(UserRepository.class);
        BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);
        EmailSender sender = mock(EmailSender.class);
        when(repository.findOneByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(new User());

        RegisterUserAction action = new RegisterUserAction(repository,encoder, sender);
        action.handle(request);

        verify(repository, times(1)).save(any());
        verify(sender, times(1)).send(any());
    }
}