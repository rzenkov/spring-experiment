package ru.zenkovr.todotaskapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void it_shoud_confirm_email_with_token() {
        // Arrange
        User user = new User();
        String token = "abc";
        user.setEmailConfirmationToken(token);

        //Act
        user.confirmEmail(token);

        //Assert
        assertTrue(user.isEmailConfirmed());
    }
    @Test
    void it_should_not_confirm_email_if_token_different(){
        User user = new User();
        String token = "abc";
        user.setEmailConfirmationToken(token);

        user.confirmEmail("bca");

        assertFalse(user.isEmailConfirmed());
    }
}