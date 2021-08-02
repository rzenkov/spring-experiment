package ru.zenkovr.todotaskapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zenkovr.todotaskapi.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findOneByEmail(String email);
    Optional<User> findOneByEmailConfirmationToken(String email);
}
