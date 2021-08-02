package ru.zenkovr.todotaskapi.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zenkovr.todotaskapi.model.User;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;

    public UserDTO(User user){
        this.id = user.getId();
        this.username=user.getUsername();
        this.email=user.getEmail();
    }
}
