package ru.zenkovr.todotaskapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.zenkovr.todotaskapi.email.Mailable;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSender {
    private JavaMailSender sender;
    @Autowired
    public EmailSender(JavaMailSender sender){
        this.sender = sender;
    }
    public void send(Mailable mailable) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(mailable.to());
        helper.setSubject(mailable.subject());
        helper.setText(mailable.message(),true);
        sender.send(message);
    }
}
