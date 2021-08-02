package ru.zenkovr.todotaskapi.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zenkovr.todotaskapi.dao.UserRepository;
import ru.zenkovr.todotaskapi.dto.request.RegisterUserRequest;
import ru.zenkovr.todotaskapi.email.Mailable;
import ru.zenkovr.todotaskapi.exception.UserAlreadyRegisteredException;
import ru.zenkovr.todotaskapi.model.User;
import ru.zenkovr.todotaskapi.service.EmailSender;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterUserAction {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;


    @Autowired
    public RegisterUserAction(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailSender emailSender){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.emailSender=emailSender;
    }
    public User handle(RegisterUserRequest registerUserRequest) throws MessagingException {

        Optional<User> found = userRepository.findOneByEmail(registerUserRequest.getEmail());
        ensureUserNotRegistered(found);
        User newUser = registerUser(registerUserRequest);
        sendConfirmationEmail(newUser);
        return newUser;
    }
    private void ensureUserNotRegistered(Optional<User> user){
        if(user.isPresent()){
            throw new UserAlreadyRegisteredException(String.format("User with email %s already registered!",user.get().getEmail()));
        }
    }
    private User registerUser(RegisterUserRequest registerUserRequest){
        User user = new User(
                registerUserRequest,
                passwordEncoder.encode(registerUserRequest.getPassword()),
                UUID.randomUUID().toString()
        );
        return userRepository.save(user);
    }
    private void sendConfirmationEmail(User user) throws MessagingException {
        String token = user.getEmailConfirmationToken();
        emailSender.send(new Mailable() {
            @Override
            public String to() {
                return user.getEmail();
            }

            @Override
            public String subject() {
                return "Email confirmation";
            }

            @Override
            public String message() {
                String confirmationUrl = generateConfirmationUrl(token);
                return String.format("<h2>Hello %s<h2><p>Please confirm your email.</p><div style=\"text-center\"><a style=\"padding:8px;background:#abc;display:inline-block;\" href=\"%s\">Confirm email</a></div>", user.getUsername(),confirmationUrl );
            }
            private String generateConfirmationUrl(String confirmationToken){
                return String.format("http://localhost:8080/auth/confirm-email?token=%s",confirmationToken);
            }
        });
    }
}
