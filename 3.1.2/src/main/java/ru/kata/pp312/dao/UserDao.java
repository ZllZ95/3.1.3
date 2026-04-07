package ru.kata.pp312.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.pp312.model.User;

import java.util.Optional;


public interface UserDao extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
