package ru.zenkovr.todotaskapi.email;

public interface Mailable {
    public String to();
    public String subject();
    public String message();
}
