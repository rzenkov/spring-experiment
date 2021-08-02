package ru.zenkovr.todotaskapi.email;

import ru.zenkovr.todotaskapi.model.User;

public class WelcomeMessage implements Mailable{

    private final String username;
    private final String email;

    public WelcomeMessage(User user){
       this.username = user.getUsername();
       this.email= user.getEmail();
    }
    @Override
    public String to() {
        return this.email;
    }

    @Override
    public String subject() {
        return "You are welcome!";
    }

    @Override
    public String message() {
        return String.format("Dear %s\n, You are succesfully registred at service and might to start using it.", this.username);
    }
}
