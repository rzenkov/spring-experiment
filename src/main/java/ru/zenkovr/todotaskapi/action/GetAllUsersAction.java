package ru.zenkovr.todotaskapi.action;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zenkovr.todotaskapi.dao.UserRepository;
import ru.zenkovr.todotaskapi.dto.response.UserCollection;
import ru.zenkovr.todotaskapi.model.User;

@Service
public class GetAllUsersAction {
    private final UserRepository repository;
    public GetAllUsersAction(UserRepository repository){
        this.repository =repository;
    }

    public UserCollection handle(Pageable pageable){
        Page<User> users = repository.findAll(pageable);

        return new UserCollection(users);
    }
}
