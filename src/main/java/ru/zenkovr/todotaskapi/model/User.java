package ru.zenkovr.todotaskapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zenkovr.todotaskapi.dto.request.RegisterUserRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name="users", indexes = {@Index(columnList = "email"), @Index(columnList = "emailConfirmationToken")})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size (max=50)
    private String username;

    @NotBlank
    @Size (max=50)
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotBlank
    @Size (max=50)
    private String emailConfirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date emailConfirmedAt;

    public User(RegisterUserRequest registerUserRequest, String hashedPassword, String emailConfirmationToken){
        this.email = registerUserRequest.getEmail();
        this.username=registerUserRequest.getUsername();
        this.password = hashedPassword;
        this.emailConfirmationToken=emailConfirmationToken;
        this.createdAt=new Date();
    }

    public Boolean isEmailConfirmed(){
        return !emailConfirmedAt.equals(null);
    }

    public void confirmEmail(String token){
        if(emailConfirmationToken.equals(token)){
            emailConfirmedAt=new Date();
        }
    }
}
