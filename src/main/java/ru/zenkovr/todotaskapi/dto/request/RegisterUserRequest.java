package ru.zenkovr.todotaskapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotNull
    @NotBlank
    @Email
    @Size(min = 4, max = 20)
    private String email;
}
