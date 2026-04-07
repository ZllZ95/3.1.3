package ru.kata.pp312.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.pp312.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> index();
    User show(int id);
    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
    User findByUsername(String username);
}
