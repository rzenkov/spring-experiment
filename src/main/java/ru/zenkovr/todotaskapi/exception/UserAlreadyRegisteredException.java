package ru.zenkovr.todotaskapi.exception;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException(String message){
        super(message);
    }
}
