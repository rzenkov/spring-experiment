package ru.zenkovr.todotaskapi.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zenkovr.todotaskapi.dao.UserRepository;
import ru.zenkovr.todotaskapi.dto.response.UserDTO;
import ru.zenkovr.todotaskapi.email.WelcomeMessage;
import ru.zenkovr.todotaskapi.model.User;
import ru.zenkovr.todotaskapi.service.EmailSender;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ConfirmEmailAction {
    private final UserRepository userRepository;
    private final EmailSender emailSender;

    @Autowired
    public ConfirmEmailAction(UserRepository userRepository, EmailSender sender){
        this.userRepository=userRepository;
        this.emailSender=sender;
    }

    public UserDTO handle(String token) throws MessagingException {
        Optional<User> user = userRepository.findOneByEmailConfirmationToken(token);
        if(user.isEmpty()){
            throw new EntityNotFoundException("Your can not confrirm email");
        }
        User confirmedUser = confirmUser(user.get(), token);
        sendWelcomeMessage(confirmedUser);
        return new UserDTO(confirmedUser);
    }
    private User confirmUser(User user, String token){
        user.confirmEmail(token);
        return userRepository.save(user);
    }
    private void sendWelcomeMessage(User user) throws MessagingException {
        emailSender.send(new WelcomeMessage(user));
    }
}
