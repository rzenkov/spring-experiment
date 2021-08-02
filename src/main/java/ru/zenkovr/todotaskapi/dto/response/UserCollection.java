package ru.zenkovr.todotaskapi.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import ru.zenkovr.todotaskapi.model.User;

@Data
@NoArgsConstructor
public class UserCollection {
    Page<UserDTO> data;

    public UserCollection(Page<User> users){
        this.data = users.map(user -> {
            return new UserDTO(user);
        });
    }
}
